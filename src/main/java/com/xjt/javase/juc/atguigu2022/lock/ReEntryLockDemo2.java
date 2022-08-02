package com.xjt.javase.juc.atguigu2022.lock;

public class ReEntryLockDemo2 {
    public synchronized void m1() {
        //指的是可重复可递归调用的锁，在外层使用之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁
        System.out.println(Thread.currentThread().getName() + "\t" + "-----come in m1");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t-----end m1");
    }

    public synchronized void m2() {
        System.out.println("-----m2");
        m3();
    }

    public synchronized void m3() {
        System.out.println("-----m3");
    }

    public static void main(String[] args) {
        ReEntryLockDemo2 reEntryLockDemo = new ReEntryLockDemo2();

        reEntryLockDemo.m1();
    }
}
