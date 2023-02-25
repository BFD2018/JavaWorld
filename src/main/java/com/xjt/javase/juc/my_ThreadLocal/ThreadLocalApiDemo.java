package com.xjt.javase.juc.my_ThreadLocal;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class House{
//    public ThreadLocal<Integer> threadLocal = new ThreadLocal<>(){
//        @Override
//        protected Integer initialValue() {
//            return 0;
//        }
//    };

    //JDK8 推荐使用下面这种写法（更上面的功能一样）
    public ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->0);

    public void saleHouse(){
        //T get():返回当前线程的此线程局部变量的副本中的值。
        Integer value = threadLocal.get();
        value++;
        //void set(T value):将当前线程的此线程局部变量的副本设置为指定的值。
        threadLocal.set(value);
    }
}

class MyData{
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void add(){
        threadLocal.set(1 + threadLocal.get());
    }
}

class MyBook{
    public ThreadLocal<MyData> threadLocal = new ThreadLocal<>();
}


public class ThreadLocalApiDemo {
    @SneakyThrows
    public static void main(String[] args) {
//        m_api();

//        m_unremove();

//        m_remove();

        //ThreadLocal 初始化没有指定初始值  get方法有bug
        MyBook myBook = new MyBook();
        System.out.println(myBook.threadLocal.get());
    }

    /**
     * ThreadLocal 用完没有remove 线程复用时 ThreadLocalMap的会保留上一次的内容 bug！！！
     * @throws InterruptedException
     */
    public static void m_unremove() throws InterruptedException {
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 8; i++) {
            threadPool.submit(() ->{
                Integer before = myData.threadLocal.get();
                myData.add();
                Integer after = myData.threadLocal.get();

                System.out.println(Thread.currentThread().getName() + "\t before=" + before + " after=" + after);
            });
        }

        TimeUnit.SECONDS.sleep(1);
        threadPool.shutdown();
    }

    public static void m_remove() throws InterruptedException {
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 8; i++) {
            threadPool.submit(() ->{
                Integer before = null;
                Integer after = null;
                try {
                    before = myData.threadLocal.get();
                    myData.add();
                    after = myData.threadLocal.get();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    myData.threadLocal.remove();
                }

                System.out.println(Thread.currentThread().getName() + "\t before=" + before + " after=" + after);
            });
        }

        TimeUnit.SECONDS.sleep(1);
        threadPool.shutdown();
    }

    public static void m_api() {
        House house = new House();
        new Thread(()->{
            try {
                //t1线程卖3套房子
                for (int i = 0; i < 3; i++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"卖出:"+house.threadLocal.get());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //void remove():删除此线程局部变量的当前线程的值
                //在阿里巴巴手册中有说明,尽量在代理中使用try-finally块进行回收
                house.threadLocal.remove();
                //下面获取到的值是线程的初始值0
                System.out.println(Thread.currentThread().getName()+"\t **********" + house.threadLocal.get());
            }
        },"t1").start();

        new Thread(()->{
            try{
                for (int i = 1; i <=5; i++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"卖出:"+house.threadLocal.get());
            }catch (Exception e){
                e.getStackTrace();
            }finally {
                house.threadLocal.remove();
            }
        },"t2").start();

        new Thread(()->{
            try{
                for (int i = 1; i <=8; i++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"卖出:"+house.threadLocal.get());
            }catch (Exception e){
                e.getStackTrace();
            }finally {
                house.threadLocal.remove();
            }
        },"t3").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"卖出了:"+house.threadLocal.get());
    }
}
