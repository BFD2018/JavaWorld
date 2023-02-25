package com.xjt.javase.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//集齐7颗龙珠就能召唤神龙
public class CyclicBarrierDemo {
    private static Integer SIZE = 7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(SIZE,()->{
            System.out.println(Thread.currentThread().getName() + "\t 召唤龙珠");
        });

        for (int i = 1; i <=SIZE; i++) {
            final int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到了第"+temp+"颗龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"t"+i).start();
        }
    }
}
