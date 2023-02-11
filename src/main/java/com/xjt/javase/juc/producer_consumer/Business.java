package com.xjt.javase.juc.producer_consumer;

public class Business {
    // 需求 先生产 然后再消费 循环往复
    // 定义一个开关 true允许生产（不允许消费）   false消费（不允许生产）
    private boolean beShouleProducer = true;

    public synchronized void producer(){
        // 不允许生产时 为false时
        if(!beShouleProducer){
            try {
                // 生产者线程在这里等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"执行第"+i+"次");
            try {
                // 线程在这里休眠 1 毫秒
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 生产完毕 该消费线程了 将可否生产设置为false
            beShouleProducer = false;
            // 唤醒等待的其他线程
            this.notifyAll();
        }
    }
    public synchronized void consumer(){
        // 如果不能消费（为true）
        if (beShouleProducer){
            try {
                // 消费者线程在这里等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"执行第"+i+"次");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 消费者消费完毕后 将可否生产设置为true
        beShouleProducer = true;
        // 唤醒等待的其他线程
        this.notifyAll();
    }
}
