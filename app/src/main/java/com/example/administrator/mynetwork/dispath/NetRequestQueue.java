package com.example.administrator.mynetwork.dispath;


/**
 * auther：wzy
 * date：2016/11/16 22 :10
 * desc:维护一个请求队列
 */

public class NetRequestQueue {

    private NetRequestQueue() {
    }

    public Request next() {
        return getOutFromQueue();
    }

    private static NetRequestQueue mRequestQueue;
    /**
     * core for save queue
     */
    public Request mMostNewRequest;
    /**
     * for transit
     */
    public Request temp;

    /**
     * add　request to queue
     *
     * @param request
     */
    public NetRequestQueue addToQueue(Request request) {
        if (mMostNewRequest == null) {
            synchronized (NetRequestQueue.class) {
                if (mMostNewRequest == null) {
                    mMostNewRequest = request;
                }
            }
        } else {
            request.next = mMostNewRequest;
            mMostNewRequest = request;
        }
        return this;
    }

    /**
     * check out request from queue
     *
     * @return Request
     */
    public Request getOutFromQueue() {
        if (mMostNewRequest == null) {
            return null;
        }
        temp = mMostNewRequest;
        mMostNewRequest = mMostNewRequest.next;
        temp.next = null;
        return temp;
    }


    /**
     * 内部维护一个单例请求队列
     *
     * @return
     */
    public static NetRequestQueue getSingleRequestQueue() {
        if (mRequestQueue == null) {
            synchronized (NetWorkExecute.class) {
                if (mRequestQueue == null) {
                    mRequestQueue = new NetRequestQueue();
                }
            }
        }
        return mRequestQueue;
    }

    private NetWorkExecute.Method mDefaultMethod = NetWorkExecute.Method.GET;

    /**
     * 设置请求方式 默认GET
     *
     * @param mDefaultMethod
     */
    public NetRequestQueue setMethod(NetWorkExecute.Method mDefaultMethod) {
        this.mDefaultMethod = mDefaultMethod;
        return this;
    }

    public void start() {
        if (mRequestQueue == null) {
            return;
        }
        do {
            NetWorkExecute netWorkExecute =
                    new NetWorkExecute(mRequestQueue.mMostNewRequest.getResponseListener(),
                            mDefaultMethod, mRequestQueue.mMostNewRequest.getRequestUrl(),
                            mRequestQueue.mMostNewRequest.getRequestParameter());
        } while (mRequestQueue.next() != null);
    }
}
