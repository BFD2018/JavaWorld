package com.xjt.javase.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptThreadMethod1 {
    public static volatile Boolean isStop = false;
    public static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
//        m_volatile();

//        m_atomicBoolean();

//        m_interrupt();

        /*3大中断方法*/
        System.out.println("**********中断之前");
        Thread.currentThread().interrupt();
    }

    public static void m_volatile(){
        new Thread(() ->{
            while (true){
                if(isStop){
                    System.out.println(Thread.currentThread().getName() + " --线程被打断，isStop==true");
                    break;
                }
                System.out.println(" ---------hello world!");
            }
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            isStop = true;
        },"t2").start();
    }

    public static void m_atomicBoolean(){
        new Thread(() ->{
            while (true){
                if(atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName() + " --线程被打断，atomicBoolean.get()==true");
                    break;
                }
                System.out.println(" ---------hello world!");
            }
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            atomicBoolean.set(true);
        },"t2").start();
    }

    public static void m_interrupt(){
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " --线程被打断，Thread.currentThread().isInterrupted()==true");
                    break;
                }
                System.out.println(" ---------hello world!");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            t1.interrupt();
        },"t2").start();
    }
}
