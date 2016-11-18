package com.example.administrator.mynetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.mynetwork.dispath.NetRequestQueue;
import com.example.administrator.mynetwork.dispath.Request;
import com.example.administrator.mynetwork.dispath.ResponseListener;
import com.example.administrator.mynetwork.dispath.StringRequest;


public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*NetWorkExecute netWorkExecute = new NetWorkExecute(new ResponseListener() {
            @Override
            public void sucessBack(String result) {
                Log.i(TAG, "sucessBack: " + result);
                Toast.makeText(MainActivity.this, "结果是：" + result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failBack(Exception e) {

            }
        }, "http://www.baidu.com");
        netWorkExecute.start();*/

        StringRequest request = new StringRequest("http://www.baidu.com", new ResponseListener() {
            @Override
            public void sucessBack(String result) {
                Log.i(TAG, "成功返回的结果" + result);
            }

            @Override
            public void failBack(Exception e) {

            }
        });
        NetRequestQueue.getSingleRequestQueue().addToQueue(request).start();
    }
}
