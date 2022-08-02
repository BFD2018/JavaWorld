package com.xjt.javase.myCollection.homework;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HomeWork3 {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("jack",650);
        map.put("tom",1200);
        map.put("smith",2900);
        System.out.println(map);

        map.put("jack",2600);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int val = entry.getValue() + 100;
            map.put(key,val);
        }

        map.keySet().forEach((item)->{
            System.out.println(item);
        });

        for (Integer value : map.values()) {
            System.out.println(value);
        }
    }
}
