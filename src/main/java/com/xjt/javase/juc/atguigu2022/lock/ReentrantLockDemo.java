package com.xjt.javase.juc.atguigu2022.lock;


import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int number = 50;
//    ReentrantLock lock = new ReentrantLock();
    ReentrantLock lock = new ReentrantLock(true);

    public void sale(){
        lock.lock();
        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"卖出第：\t"+(number--)+"\t 还剩下:"+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo{
    public static void main(String[] args){
        Ticket ticket = new Ticket();

        new Thread(() -> { for (int i = 0; i <55; i++)  ticket.sale(); },"a").start();
        new Thread(() -> { for (int i = 0; i <55; i++)  ticket.sale(); },"b").start();
        new Thread(() -> { for (int i = 0; i <55; i++)  ticket.sale(); },"c").start();
    }
}
