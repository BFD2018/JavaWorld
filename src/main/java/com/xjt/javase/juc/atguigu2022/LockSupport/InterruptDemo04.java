package com.xjt.javase.juc.atguigu2022.LockSupport;

public class InterruptDemo04 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());
        System.out.println("-----1");

        Thread.currentThread().interrupt();     //中断标志位设置为true

        System.out.println("-----2");
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());     //true
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted()); //false
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());     //false
    }
}
