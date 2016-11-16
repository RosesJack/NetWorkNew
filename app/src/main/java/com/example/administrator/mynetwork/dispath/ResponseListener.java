package com.example.administrator.mynetwork.dispath;

/**
 * auther：wzy
 * date：2016/11/16 21 :19
 * desc:
 */

public interface ResponseListener {
    void sucessBack(String result);

    void failBack(Exception e);
}
