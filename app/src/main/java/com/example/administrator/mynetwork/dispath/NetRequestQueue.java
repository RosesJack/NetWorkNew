package com.example.administrator.mynetwork.dispath;


/**
 * auther：wzy
 * date：2016/11/16 22 :10
 * desc:维护一个请求队列
 */

public class NetRequestQueue {
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
    public void addToQueue(Request request) {
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
}
