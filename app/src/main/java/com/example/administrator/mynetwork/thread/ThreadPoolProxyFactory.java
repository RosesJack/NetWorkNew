package com.example.administrator.mynetwork.thread;

/**
 * Created by Administrator on 2016/10/14.
 */
public class ThreadPoolProxyFactory {
    /**
     * 普通线程池
     */
    static ThreadPoolProxy mNormalThreadPoolProxy;
    /**
     * 下载线程
     */
    static ThreadPoolProxy mDownLoadThreadPoolProxy;
    /**
     * 一般网络请求线程池
     */
    static ThreadPoolProxy mNormalNetWorkThreadPoolProxy;

    public static ThreadPoolProxy getNormalThreadPoolProxy() {
        if (mNormalThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mNormalThreadPoolProxy == null) {
                    mNormalThreadPoolProxy = new ThreadPoolProxy(5, 5);
                }
            }
        }
        return mNormalThreadPoolProxy;
    }

    public static ThreadPoolProxy getDownLoadThreadPoolProxy() {
        if (mDownLoadThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mDownLoadThreadPoolProxy == null) {
                    mDownLoadThreadPoolProxy = new ThreadPoolProxy(3, 3);
                }
            }
        }
        return mDownLoadThreadPoolProxy;
    }

    /**
     * 获取一般网络请求的线程池
     *
     * @return ThreadPoolProxy
     */
    public static ThreadPoolProxy getNormalNetWorkThreadPoolProxy() {
        if (mNormalNetWorkThreadPoolProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mNormalNetWorkThreadPoolProxy == null) {
                    mNormalNetWorkThreadPoolProxy = new ThreadPoolProxy(4, 4);
                }
            }
        }
        return mNormalNetWorkThreadPoolProxy;
    }
}
