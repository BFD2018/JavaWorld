package com.xjt.javase.designPattern.singleton;


import java.util.concurrent.atomic.AtomicLong;

class Counter {
    private Counter(){
        System.out.println("init...");
    }

    private static class CounterHolder{
        private static final Counter counter = new Counter();
    }


    public static final Counter getInstance(){
        return CounterHolder.counter;
    }

    private AtomicLong online = new AtomicLong();

    public long getOnline(){
        return online.get();
    }

    public long add(){
        return online.incrementAndGet();
    }
}

public class WebCounterDemo {
    public static void main(String[] args) {
        Counter counter = Counter.getInstance();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                counter.add();
                System.out.println(counter.getOnline());
            }).start();
        }
    }
}
