package com.xjt.javase.juc.createThread;

public class ThreadYield {
    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "线程拿到执行权 " + i);
            if(i % 10 == 0){
                Thread.yield();
            }

            System.out.println(getName() + ":" + i);
        }
    }
}
