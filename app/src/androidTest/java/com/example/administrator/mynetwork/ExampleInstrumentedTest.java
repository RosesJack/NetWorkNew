package com.example.administrator.mynetwork;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.administrator.mynetwork.dispath.NetWorkExecute;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.administrator.mynetwork", appContext.getPackageName());
    }
    @Test
    public void test2(){
        NetWorkExecute netWorkExecute = new NetWorkExecute(null, null);
        Map<String, String> map = new HashMap<>();
        map.put("name", "wang");
        map.put("age", "15");
        String s = netWorkExecute.mapToParameterStr(map);
        System.out.println(s);
    }
}
