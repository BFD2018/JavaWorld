package com.xjt.javase.juc.producer_consumer;

public class PCMain {
    public static void main(String[] args) {
        Business business = new Business();
        new Thread(() -> {
            while (true) {
                business.producer();
            }
        }, "生产者").start();

        new Thread(() -> {
            while (true) {
                business.consumer();
            }
        }, "消费者").start();
    }
}
