package com.xjt.javase.juc.JMM;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JMMDemo {
    private static boolean initFlag = false;

    private volatile static int counter = 0;

    public static void refresh(){
        System.out.println("refresh data.......");
        initFlag = true;
        System.out.println("refresh data success.......");
    }


    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
            while (!initFlag){
                System.out.println("runing...counter==" + counter);
                counter++;
            }
            System.out.println("线程：" + Thread.currentThread().getName()
                    + "当前线程嗅探到initFlag的状态的改变");
        },"threadA");
        threadA.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(()->{
            refresh();
        },"threadB");
        threadB.start();
    }
}
