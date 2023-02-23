package com.xjt.javase.juc.locksupport;

import java.util.concurrent.TimeUnit;

public class IntertuptDemo02 {
    public static void main(String[] args) {
        m1_volatile();
    }

    public static void m1_volatile() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {//一旦发现中断标志位被修改
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()被修改为true，程序终止");
                    break;
                }
                System.out.println("t1 ------hello interrupt ");	//----------如果没停止，那就一直打印
            }
        }, "t1");
        t1.start();

        System.out.println(Thread.currentThread().getName() + "-----t1的默认中断标志位："+ t1.isInterrupted());

        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            t1.interrupt();		//把t1中断
        },"t2").start();
    }
}
