package com.xjt.javase.juc.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Book{
    private int id;
    private String name;
}


public class AtomicStampedReferenceDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
//        m_base();

        //多线程用法
//        m_aba();

        m_dealABA();

//        new Thread(()->{
//            int stamp = stampedReference.getStamp();
//            System.out.println(Thread.currentThread().getName()+"\t 第1次版本号"+stamp+"\t值是"+stampedReference.getReference());
//            //暂停1秒钟t3线程
//            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//
//            stampedReference.compareAndSet(100,101,stampedReference.getStamp(),stampedReference.getStamp()+1);
//            System.out.println(Thread.currentThread().getName()+"\t 第2次版本号"+stampedReference.getStamp()+"\t值是"+stampedReference.getReference());
//            stampedReference.compareAndSet(101,100,stampedReference.getStamp(),stampedReference.getStamp()+1);
//            System.out.println(Thread.currentThread().getName()+"\t 第3次版本号"+stampedReference.getStamp()+"\t值是"+stampedReference.getReference());
//        },"t3").start();
//
//        new Thread(()->{
//            int stamp = stampedReference.getStamp();
//            System.out.println(Thread.currentThread().getName()+"\t 第1次版本号"+stamp+"\t值是"+stampedReference.getReference());
//            //保证线程3完成1次ABA
//            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
//            boolean result = stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
//            System.out.println(Thread.currentThread().getName()+"\t 修改成功否"+result+"\t最新版本号"+stampedReference.getStamp() + "最新的值\t"+stampedReference.getReference());
//        },"t4").start();
    }

//    static AtomicInteger num = new AtomicInteger(100);
//    private static AtomicStampedReference<AtomicInteger> stampedReference2 = new AtomicStampedReference<>(num,1);
//    public static void m_dealABA2() {
//        new Thread(()->{
//            System.out.println(Thread.currentThread().getName() + "\t come in");
//            boolean b;
//
//            //true
//            b = stampedReference2.compareAndSet(num, num., stampedReference.getStamp(), stampedReference.getStamp() + 1);
//            System.out.println(Thread.currentThread().getName() + " 第1次修改：" + b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
//
//            //false
//            b = stampedReference2.compareAndSet(200,300, stampedReference.getStamp(),stampedReference.getStamp()+1);
//            System.out.println(Thread.currentThread().getName() + " 第2次修改:" + b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
//
//            //false
//            b = stampedReference2.compareAndSet(300,100, stampedReference.getStamp(),stampedReference.getStamp()+1);
//            System.out.println(Thread.currentThread().getName() + " 第3次修改:" + b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
//        },"t1").start();
//
//        System.out.println("------------------------------>");
//
//        new Thread(() ->{
//            int stamp = stampedReference.getStamp();
//            System.out.println(Thread.currentThread().getName() + "\t come in stamp=" + stamp);
//            try {
//                TimeUnit.SECONDS.sleep(5);      //让出CPU执行权
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            boolean b = stampedReference.compareAndSet(100,500,stamp,stamp+1);
//            System.out.println(Thread.currentThread().getName() + "\t" + b + "\t" +atomicInteger.get());
//        },"t2").start();
//    }

    // TODO
    //  Integer类型超过[-127,128) 范围 ，用 == 判断都是false
    public static void m_dealABA() {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t come in");
            boolean b;

            //true
            b = stampedReference.compareAndSet(100, 200, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第1次修改：" + b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

            //false
            b = stampedReference.compareAndSet(200,300, stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + " 第2次修改:" + b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

            //false
            b = stampedReference.compareAndSet(300,100, stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + " 第3次修改:" + b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
        },"t1").start();

        System.out.println("------------------------------>");

        new Thread(() ->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t come in stamp=" + stamp);
            try {
                TimeUnit.SECONDS.sleep(5);      //让出CPU执行权
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean b = stampedReference.compareAndSet(100,500,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName() + "\t" + b + "\t" +atomicInteger.get());
        },"t2").start();
    }

    public static void m_aba() {
        new Thread(() ->{
            boolean b = atomicInteger.compareAndSet(100, 101);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(101, 100);
        },"t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
            boolean b = atomicInteger.compareAndSet(100, 200);
            System.out.println(b + "\t" +atomicInteger.get());
        },"t2").start();
    }


    public static void m_base() {
        Book javaBook = new Book(1, "java");
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(javaBook, 1);
        System.out.println(atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());

        Book pythonBook = new Book(2, "python");
        boolean b;
        b = atomicStampedReference.compareAndSet(javaBook, pythonBook, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println(b + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());

        b = atomicStampedReference.compareAndSet(javaBook, pythonBook, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println(b + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
    }
}
