package com.xjt.javase.juc.atguigu2022.lock;

import java.util.concurrent.TimeUnit;


public class Lock8Demo {
    /**
     * - 题目：谈谈你对多线程锁的理解，8锁案例说明
     * - 口诀：线程 操作 资源类
     * 1. 标准访问有ab两个线程，请问先打印邮件还是短信?	邮件
     * 2. a里面故意停3秒？	邮件
     * 3. 添加一个普通的hello方法，请问先打印邮件还是hello？	hello
     * 4. 有两部手机，请问先打印邮件（这里有个3秒延迟）还是短信?	短信
     * 5.有两个静态同步方法（synchroized前加static,3秒延迟也在），有1部手机，先打印邮件还是短信？	邮件
     * 6.有两个静态同步方法（synchroized前加static,3秒延迟也在），有2部手机，先打印邮件还是短信？	邮件
     * 7.一个静态同步方法，一个普通同步方法，请问先打印邮件还是手机？	短信
     * 8.两个手机，一个静态同步方法，一个普通同步方法，请问先打印邮件还是手机？	短信
     */
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            phone.sendEmail();
        },"a").start();

        //暂停毫秒，保证a线程先启动
        try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            phone.sendSMS();
//            phone2.sendSMS();
//            phone.hello();
        },"b").start();
    }
}

class Phone{
    public static synchronized void sendEmail(){
        try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("-------------sendEmail");

    }
    public synchronized void sendSMS(){         //static
        System.out.println("-------------sendSMS");
    }
    public void hello(){
        synchronized (this){

        }
        System.out.println("-------------hello");
    }
}
