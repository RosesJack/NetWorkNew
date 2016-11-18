package com.example.administrator.mynetwork;

import com.example.administrator.mynetwork.dispath.NetWorkExecute;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        NetWorkExecute netWorkExecute = new NetWorkExecute(null, null);
        Map<String, String> map = new HashMap<>();
        map.put("name", "wang");
        map.put("age", "15");
        String s = netWorkExecute.mapToParameterStr(map);
        System.out.println(s);
    }
}