package com.example.administrator.mynetwork.dispath;

<<<<<<< HEAD
import java.util.Map;

/**
 * auther：wzy
 * date：2016/11/17 00 :32
 * desc: 请求的基类
 */

public abstract class Request {
    private ResponseListener mResponseListener;

    public void setResponseListener(ResponseListener mResponseListener) {
        this.mResponseListener = mResponseListener;
    }

    public Request(ResponseListener mResponseListener) {
        this.mResponseListener = mResponseListener;
    }

    /**
     * 请求地址
     */
    private String requestUrl;
    /**
     * 请求参数
     */
    private Map<String, String> requestParameter;
    /**
     * 相应结果
     */
    public String response;
    public Request next;

    public Object getResponse() {
        return parseResponse(response);
    }

    protected abstract Object parseResponse(String response);

    public ResponseListener getResponseListener() {
        return mResponseListener;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public Request setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        return this;
    }

    public Map<String, String> getRequestParameter() {
        return requestParameter;
    }

    public Request setRequestParameter(Map<String, String> requestParameter) {
        this.requestParameter = requestParameter;
        return this;
    }
=======
/**
 * auther：wzy
 * date：2016/11/17 00 :32
 * desc: 请求
 */

public class Request {
    public String flag;
    public Request next;
>>>>>>> 30df204c923f90c17d03ef64bb9110ab66645af8
}
