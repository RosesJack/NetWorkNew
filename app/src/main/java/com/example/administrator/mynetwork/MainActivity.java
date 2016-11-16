package com.example.administrator.mynetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.mynetwork.dispath.NetRequestQueue;
import com.example.administrator.mynetwork.dispath.NetWorkExecute;
import com.example.administrator.mynetwork.dispath.Request;
import com.example.administrator.mynetwork.dispath.ResponseListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
        NetRequestQueue queue = new NetRequestQueue();
        for (int i = 0; i < 10; i++) {
            Request request = new Request();
            request.flag = "第" + i + "个";
            queue.addToQueue(request);
        }
        for (int i = 0; i < 10; i++) {
            Log.i(TAG, "取出队列并展示：" + queue.getOutFromQueue().flag);
        }
    }
}
