package com.xjt.javase.juc.locks;

import java.util.concurrent.TimeUnit;

//①. 标准访问有a b两个线程,请问先打印邮件还是短信       --- sendEmail
public class Lock8Demo01 {
    public static void main(String[] args) {
        IPhone phone = new IPhone();//资源类1
        IPhone phone2 = new IPhone();//资源类2

        new Thread(() -> {
            phone.sendEmail();
        },"a").start();

        //暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
//            phone.sendSMS();
//            phone.hello();
            phone2.sendSMS();
        },"b").start();
    }

}

class IPhone{ //资源类
    public static synchronized void sendEmail() {
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-------sendEmail");
    }

    public static synchronized void sendSMS()
    {
        System.out.println("-------sendSMS");
    }

    public void hello()
    {
        System.out.println("-------hello");
    }
}