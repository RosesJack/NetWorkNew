package com.example.administrator.mynetwork.dispath;

import java.util.Map;

/**
 * auther：wzy
 * date：2016/11/18 22 :40
 * desc:
 */

public class StringRequest extends Request {


    public StringRequest(String url, Map<String, String> map, ResponseListener mResponseListener) {
        super(url, map, mResponseListener);
    }

    public StringRequest(String url, ResponseListener mResponseListener) {
        super(url, mResponseListener);
    }

    @Override
    protected Object parseResponse(String response) {
        return response;
    }

}
