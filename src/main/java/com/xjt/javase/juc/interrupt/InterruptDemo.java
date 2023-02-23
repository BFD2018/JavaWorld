package com.xjt.javase.juc.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    public static void main(String[] args) {
//        m5();
        m4();
    }

    public static void m5() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("-----isInterrupted() = true,程序结束。");
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    //线程的中断标志位重新设置为false,无法停下,需要再次掉interrupt()设置true
                    Thread.currentThread().interrupt();//???????
                    e.printStackTrace();
                }
                System.out.println("------hello Interrupt");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            t1.interrupt();//修改t1线程的中断标志位为true
        }, "t2").start();
    }

    /**
     * 中断为true后,并不是立刻stop程序
     */
    public static void m4() {
        //中断为true后,并不是立刻stop程序
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 300; i++) {
                System.out.println("------i: " + i);
            }
            System.out.println("t1.interrupt()调用之后02: " + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        System.out.println("t1.interrupt()调用之前,t1线程的中断标识默认值: " + t1.isInterrupted());
        try {
            TimeUnit.MILLISECONDS.sleep(2);     //休息2ms fori遍历还没执行完
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //实例方法interrupt()仅仅是设置线程的中断状态位设置为true,不会停止线程
        t1.interrupt();
        //活动状态,t1线程还在执行中
        System.out.println("t1.interrupt()调用之后01: " + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(5000);      //休息5s fori遍历执行完了
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //非活动状态,t1线程不在执行中,已经结束执行了。
        System.out.println("t1.interrupt()调用之后03: " + t1.isInterrupted());
    }

}
