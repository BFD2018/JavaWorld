package com.xjt.javase.juc.JMM;

public class JMMDemo2 {
    private static int value=0;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "----->value++");
                value++;
            },"线程0" + i).start();

            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "----->value="+value);
            },"t" +i).start();
        }
    }

}
