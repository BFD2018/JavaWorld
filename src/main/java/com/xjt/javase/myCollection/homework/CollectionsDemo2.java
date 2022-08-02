package com.xjt.javase.myCollection.homework;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CollectionsDemo2 {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Object> arr = new ArrayList<>();
        arr.add("xiong");
        arr.add("wang");
        arr.add("li");

        List<Object> array = Collections.synchronizedList(arr);

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                int t = RandomUtil.randomInt(100);
                array.add(t + "");
                System.out.println(array);
            },"线程-"+i).start();
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "\t" + array);
    }
}
