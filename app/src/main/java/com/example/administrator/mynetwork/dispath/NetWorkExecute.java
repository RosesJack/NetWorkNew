package com.example.administrator.mynetwork.dispath;

import android.os.Looper;

import com.example.administrator.mynetwork.thread.ThreadPoolProxy;
import com.example.administrator.mynetwork.thread.ThreadPoolProxyFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import static android.R.attr.key;
import static android.R.attr.keySet;

/**
 * auther：wzy
 * date：2016/11/16 21 :17
 * desc:
 */

public class NetWorkExecute implements NetWorkCore {
    private Request mRequest;
    /**
     * out time
     * default{1000ms}
     */
    private int connectionTimeOut = 1000;
    private ThreadPoolProxy mThreadPool;

    public static enum Method {
        GET, POST;
    }

    private ResponseListener mResponseListener;
    private String mUrl;
    /**
     * GET or POST
     * default GET
     */
    private Method mMethod = Method.GET;
    public static NetRequestQueue mRequestQueue;
    private Map<String, String> mParameter;

    public NetWorkExecute(ResponseListener responseListener, String url) {
        this(responseListener, Method.GET, url, null);
    }

    public NetWorkExecute(ResponseListener responseListener, Method method, String url, Map<String, String> parameter) {
        mMethod = method;
        mResponseListener = responseListener;
        this.mUrl = url;
        mParameter = parameter;
        mThreadPool = ThreadPoolProxyFactory.getNormalNetWorkThreadPoolProxy();
    }

    public NetWorkExecute(Method method, Request request) {
        this.mRequest = request;
        this.mMethod = method;
        String urlBehind = mapToParameterStr(mRequest.getRequestParameter());
        String url = mRequest.getRequestUrl() + urlBehind;
        this.mUrl = url;
        this.mResponseListener = request.getResponseListener();
        mThreadPool = ThreadPoolProxyFactory.getNormalNetWorkThreadPoolProxy();
    }

    public NetWorkExecute(ResponseListener responseListener, Method method, String url) {
        mMethod = method;
        mResponseListener = responseListener;
        this.mUrl = url;
        mThreadPool = ThreadPoolProxyFactory.getNormalNetWorkThreadPoolProxy();
    }

    /**
     * map集合转换成url字符串
     *
     * @param map
     * @return
     */
    public String mapToParameterStr(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return "";
        }
        Set<String> keySet = map.keySet();
        StringBuffer sb = new StringBuffer();
        sb.append("?");
        int i = 0;
        for (String s : keySet) {
            if (i != 0) {
                sb.append("&");
            }
            sb.append(s + "=" + map.get(s));
            i++;
        }
        return sb.toString();
    }


    public void start() {
        loadUrl(mUrl);
    }

    public void loadUrl(String url) {
        this.mUrl = url;
        if (mMethod == Method.GET) {
            this.getRequest();
        } else if (mMethod == Method.POST) {
            this.postRequest();
        } else {
            throw new IllegalStateException("please user GET Method or POST");
        }
    }

    //TODO　POST访问网络方式
    @Override
    public void postRequest() {

    }

    @Override
    public void getRequest() {
        mThreadPool.submit(new NetWorkGet());
    }

    class NetWorkGet implements Runnable {
        @Override
        public void run() {
            try {
                URL url = new URL(mUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(connectionTimeOut);
                connection.setRequestMethod("GET");
                connection.connect();
                int code = connection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    StringBuffer sb = new StringBuffer();
                    while (bufferedReader.read() != -1) {
                        String s = bufferedReader.readLine();
                        sb.append(s);
                    }
                    if (mResponseListener != null) {
                        Looper.prepare();
                        mResponseListener.sucessBack(sb.toString());
                        Looper.loop();
                    }
                } else {
                    if (mResponseListener != null) {
                        mResponseListener.failBack(new Exception("网络错误代码：" + code));
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                if (mResponseListener != null) {
                    mResponseListener.failBack(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (mResponseListener != null) {
                    mResponseListener.failBack(e);
                }
            }
        }
    }
}
