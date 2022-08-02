package com.xjt.javase.myCollection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapDemo1 {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name", "xiong");
        map.put("age", 18);
        map.put(new Object(), new Car());

        //遍历：方式1
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        for (Object obj : entries) {
            System.out.println(obj.getClass());     //class java.util.HashMap$Node
        }

        //遍历：方式2
        map.forEach((key, val) -> {
            System.out.println("key= " + key + "\t value= " + val);
        });

        //遍历：方式3
        for (Object obj : map.keySet()) {
            System.out.println("key= " + obj + "\t value= " + map.get(obj));
        }

        //遍历：方式4
        map.entrySet().stream().forEach((item -> {
            System.out.println(item.getKey());
            System.out.println(item.getValue());
        }));

        //遍历：方式5
        Iterator<Map.Entry<Object, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> next = iterator.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
    }
}

class Car {
}
