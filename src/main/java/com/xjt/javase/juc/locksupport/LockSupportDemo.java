package com.xjt.javase.juc.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/*
(1).阻塞
 (permit默认是O,所以一开始调用park()方法,当前线程就会阻塞,直到别的线程将当前线程的permit设置为1时,
 park方法会被唤醒,然后会将permit再次设置为O并返回)
 static void park()
 static void park(Object blocker)
(2).唤醒
static void unpark(Thread thread)
 (调用unpark(thread)方法后,就会将thread线程的许可permit设置成1(注意多次调用unpark方法,不会累加,
 permit值还是1)会自动唤醒thread线程,即之前阻塞中的LockSupport.park()方法会立即返回)
 static void unpark(Thread thread)
* */
public class LockSupportDemo {
    public static void main(String[] args) {
//        m_normal();
//        m_normal2();
        m_error();

    }

    public static void m_normal(){
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t"+"coming....");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"\t"+"被B唤醒了");
        }, "t1");
        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"唤醒t1线程");
            LockSupport.unpark(t1);
        },"t2").start();
    }

    public static void m_normal2(){
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"\t"+"coming....");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"\t"+"被B唤醒了");
        }, "t1");
        t1.start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"唤醒t1线程");
            LockSupport.unpark(t1);
        },"t2").start();
    }

    public static void m_error(){
        Thread t1=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"coming....");
            LockSupport.park();
            /*
            如果这里有两个LockSupport.park(),因为permit的值为1,上一行已经使用了permit
            所以下一行被注释的打开会导致程序处于一直等待的状态
            * */
            System.out.println(Thread.currentThread().getName()+"\t"+"执行了一次park");
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"\t"+"被B唤醒了");
        },"A");
        t1.start();

        Thread t2=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"唤醒A线程");
            //有两个LockSupport.unpark(t1),由于permit的值最大为1,所以只能给park一个通行证
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
        },"B");
        t2.start();
    }
}

