package com.xjt.javase.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 题目：实现一个自旋锁，复习CAS思想
 * 自旋锁好处：
 * 循环比较获取没有类wait的阻塞。
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己特有锁5秒钟，B随后进来后发现
 * 当前有线程持有锁，所以只能通过自旋等待，直到的释放锁后品随后抢到。
 */
public class MySpinLock {
    static AtomicReference atomicReference = new AtomicReference<Thread>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "--------- lock start");
        //第一次进来时是 这个坑位 没有线程 ，线程A占住
        // 线程B进来的时候 看这个坑位不等于null 一直循环
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName() + "---------unlock over");
    }

    public static void main(String[] args) {
        MySpinLock mySpinLock = new MySpinLock();

        new Thread(()->{
            mySpinLock.myLock();
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySpinLock.myUnLock();

        },"A").start();

        // main线程 休息100ms 确保A线程先执行
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            mySpinLock.myLock();

            mySpinLock.myUnLock();
        },"B").start();
    }
}
