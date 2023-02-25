package com.xjt.javase.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchDemo {
    private static Integer SIZE = 100;

    static CountDownLatch countDownLatch = new CountDownLatch(SIZE);

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                try {
                    int andIncrement = atomicInteger.getAndIncrement();
                    System.out.println(Thread.currentThread().getName() + "\t" + andIncrement);
                } finally {
                    countDownLatch.countDown();     //
                }
            },"t"+i).start();
        }

        try {
            countDownLatch.await();     //主线程阻塞在这里 直到锁数量为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName() + "is over \t" + atomicInteger.get());
    }
}
