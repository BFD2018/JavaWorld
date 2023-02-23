package com.xjt.javase.juc.locksupport;

import java.util.concurrent.TimeUnit;

public class SynchronizedWaitNotify {
    public static void main(String[] args) {
        m1();
    }

    public static void m1() {
        Object objectLock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            }
        }, "t2").start();
    }

    public static void m2() {
        Object objectLock = new Object();

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            synchronized (objectLock){
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            try {
                objectLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
//            }
        }, "t1");
        t1.start();

        new Thread(() -> {
//            synchronized (objectLock){
            objectLock.notify();
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
//            }
        }, "t2").start();
    }

}
