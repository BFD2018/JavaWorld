package com.xjt.javase.myCollection.list;

import java.util.ArrayList;

//线程任务类
class CollectionThread implements Runnable{
    private static ArrayList<String> list = new ArrayList<String>();
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
public class ReadAndWriteTest {
    public static void main(String[] args) {
        //创建线程任务
        CollectionThread ct = new CollectionThread();

        //开启10条线程
        for (int i = 0; i < 10; i++) {
            new Thread(ct).start();
        }
    }
}
