package com.xjt.javase.juc.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
//    public static ExecutorService threadPool= Executors.newFixedThreadPool(5);//一池5个处理线程

//    public static ExecutorService threadPool=Executors.newSingleThreadExecutor();//一池一线程

    public static ExecutorService threadPool = Executors.newCachedThreadPool();     //一池N线程

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 100; i++) {
                //使用
                threadPool.execute(() -> {
                    //模拟10个用户来办理业务,每个用户就是一个来自外部的请求线程
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务~！");
                });
                try { TimeUnit.MILLISECONDS.sleep(10);  } catch (InterruptedException e) {e.printStackTrace();}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
