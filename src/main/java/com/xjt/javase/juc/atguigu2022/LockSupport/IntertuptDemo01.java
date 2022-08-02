package com.xjt.javase.juc.atguigu2022.LockSupport;

import java.util.concurrent.TimeUnit;

public class IntertuptDemo01 {
    static volatile boolean isStop = false;

    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                if(isStop){		//如果这个标志位被其他线程改为true了
                    System.out.println(Thread.currentThread().getName()+"\t isStop被修改为true 程序终止");
                    break;
                }
                System.out.println("t1 ------hello volatile");	//---------------如果没停止，那就一直打印
            }
        },"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            isStop = true;
        },"t2").start();
    }
}
