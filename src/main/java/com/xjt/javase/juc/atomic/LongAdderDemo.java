package com.xjt.javase.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

class ClickNumber{
    public int number;
    public synchronized void addNumberSynchronized(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger(0);
    public void addAtomicInteger(){
        atomicInteger.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();
    public void addLongAdder(){
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator((x,y)->x+y,0);
    public void addLongAccumulator(){
        longAccumulator.accumulate(1);
    }
}


public class LongAdderDemo {
    // 50个线程和每个线程点在100w次
    public static  final int SIZE_THREAD=50;
    public static  final int _1w=10000;

    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber=new ClickNumber();

        CountDownLatch countDownLatch1=new CountDownLatch(SIZE_THREAD);
        CountDownLatch countDownLatch2=new CountDownLatch(SIZE_THREAD);
        CountDownLatch countDownLatch3=new CountDownLatch(SIZE_THREAD);
        CountDownLatch countDownLatch4=new CountDownLatch(SIZE_THREAD);

        long startTime = System.currentTimeMillis();
        for (int i = 1 ; i <= SIZE_THREAD ; i++) {
            new Thread(()->{
                try{
                    for (int j = 1; j <= 100*_1w; j++) {
                        clickNumber.addNumberSynchronized();
                    }
                }finally {
                    countDownLatch1.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch1.await();
        long endTime = System.currentTimeMillis();
        System.out.println("addNumberSynchronized-----consTime:"+(endTime-startTime)+"毫秒"+"\t 结果是：" + clickNumber.number);

        long startTime2 = System.currentTimeMillis();
        for (int i = 1 ; i <= SIZE_THREAD ; i++) {
            new Thread(()->{
                try{
                    for (int j = 1; j <= 100*_1w; j++) {
                        clickNumber.addAtomicInteger();
                    }
                }finally {
                    countDownLatch2.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch2.await();
        long endTime2 = System.currentTimeMillis();
        System.out.println("addAtomicInteger-----consTime:"+(endTime2-startTime2)+"毫秒"+"\t 结果是：" + clickNumber.atomicInteger.get());


        long startTime3 = System.currentTimeMillis();
        for (int i = 1 ; i <= SIZE_THREAD ; i++) {
            new Thread(()->{
                try{
                    for (int j = 1; j <= 100*_1w; j++) {
                        //我们明显可以看到调用LongAdder性能最好
                        //clickNumber.add_synchronized();
                        clickNumber.addLongAdder();
                    }
                }finally {
                    countDownLatch3.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch3.await();
        long endTime3 = System.currentTimeMillis();
        System.out.println("addLongAdder-----consTime:"+(endTime3-startTime3)+"毫秒"+"\t 结果是：" + clickNumber.longAdder.sum());


        long startTime4 = System.currentTimeMillis();
        for (int i = 1 ; i <= SIZE_THREAD ; i++) {
            new Thread(()->{
                try{
                    for (int j = 1; j <= 100*_1w; j++) {
                        clickNumber.addLongAccumulator();
                    }
                }finally {
                    countDownLatch4.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch4.await();
        long endTime4 = System.currentTimeMillis();
        System.out.println("addLongAccumulator-----consTime:"+(endTime4-startTime4)+"毫秒"+"\t 结果是：" + clickNumber.longAccumulator.get());
    }
}
