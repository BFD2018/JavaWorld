package com.xjt.javase.juc.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *  可重入锁(也叫做递归锁)
 *  指的是同一线程 外层函数获得锁后,内层函数任然能获取该锁的代码
 *  在同一线程外外层方法获取锁的时候,在进入内层方法会自动获取锁
 *  *  也就是说,线程可以进入任何一个它已经标记的锁所同步的代码块
 *  **/
public class ReentrantLockDemo {
    public static void main(String[] args) {
//        Phone phone = new Phone();
//        new Thread(()->{
//            try {
//                phone.sendSms();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"t1").start();
//
//        new Thread(()->{
//            try {
//                phone.sendSms();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"t2").start();

        Phone2 phone2 = new Phone2();
        Thread t3 = new Thread(phone2);
        Thread t4 = new Thread(phone2);
        t3.start();
        t4.start();
    }
}

//synchronized 是可重入锁
class Phone{
    public synchronized void sendSms() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\tsendSms");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\tsendEmail");
    }
}

class Phone2 implements Runnable {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\tget");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            set();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\tset");
        } finally {
            lock.unlock();
        }
    }
}
