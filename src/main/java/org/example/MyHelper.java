package org.example;

import org.apache.camel.Header;

public class MyHelper {

    public MyObject[] analiseJson(MyObject[] list, @Header("k") String k) {
        for (MyObject o : list) {
            o.incSum(Integer.parseInt(k));
        }
        return list;
    }
}
