package com.example.administrator.mynetwork.dispath;

/**
 * auther：wzy
 * date：2016/11/18 22 :40
 * desc:
 */

public class StringRequest extends Request {


    public StringRequest(String response, ResponseListener mResponseListener) {
        super(response, mResponseListener);
    }

    @Override
    protected Object parseResponse(String response) {
        return response;
    }

}
