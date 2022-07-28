package com.xjt.javase.myCollection.list;

import java.util.concurrent.CopyOnWriteArrayList;

//线程任务类
class MyCollectionThread implements Runnable{
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static{
        list.add("Jack");
        list.add("Lucy");
        list.add("Jimmy");
    }

    @Override
    public void run() {
        for (String value : list) {
            System.out.println(value);
            //在读取数据的同时又向集合写入数据
            list.add("coco");
        }
    }
}

//测试类
public class ReadAndWriteTest2 {
    public static void main(String[] args) {
        //创建线程任务
        MyCollectionThread ct = new MyCollectionThread();

        //开启10条线程
        for (int i = 0; i < 10; i++) {
            new Thread(ct).start();
        }
    }
}
