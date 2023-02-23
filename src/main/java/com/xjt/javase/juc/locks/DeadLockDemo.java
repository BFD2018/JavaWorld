package com.xjt.javase.juc.locks;

import java.util.concurrent.TimeUnit;

public class DeadLockDemo {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();
        new Thread(()->{
            synchronized (lockA){
                System.out.println(Thread.currentThread().getName() + "\t" + " 自己持有A锁，期待获得B锁");

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    System.out.println(Thread.currentThread().getName() + "\t" + " 自己持有B锁，期待获得A锁");
                }
            }
        },"111").start();
        new Thread(()->{
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t" + " 自己持有B锁，期待获得A锁");

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA){
                    System.out.println(Thread.currentThread().getName() + "\t" + " 自己持有A锁，期待获得B锁");
                }
            }
        },"222").start();
    }

}
