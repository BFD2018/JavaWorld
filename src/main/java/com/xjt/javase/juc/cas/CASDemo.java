package com.xjt.javase.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
//        m_noCAS();

//        m_Atomic();

        AtomicInteger atomicInteger=new AtomicInteger(5);
        //true 2019
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t"+atomicInteger.get());
        //false 2019
        System.out.println(atomicInteger.compareAndSet(5, 2222)+"\t"+atomicInteger.get());
    }

    public static void m_noCAS() {
        System.out.println(Thread.currentThread().getName() + "-----------当前线程数量：" + Thread.activeCount());
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                countAdd();
            },"t" + i).start();
        }

        // 至少有两个线程 main + gc线程
        // 当我们自己定义的线程没有执行完时 main主线程一直在这里等待
        while (Thread.activeCount()>2) {
            Thread.yield();
        }
        System.out.println(Thread.activeCount() + "----------->count=" + count);
    }

    static volatile int count = 0;

    public synchronized static void countAdd(){
        count++;
    }

    static AtomicInteger aaa = new AtomicInteger(0);

    public static void m_Atomic(){
        System.out.println(Thread.currentThread().getName() + "-----------当前线程数量：" + Thread.activeCount());
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                aaa.getAndIncrement();
            },"t" + i).start();
        }

        while (Thread.activeCount()>2) {
            Thread.yield();
        }
        System.out.println(Thread.activeCount() + "----------->aaa=" + aaa);
    }

}
