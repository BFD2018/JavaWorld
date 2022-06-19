package com.xjt.javase.myCollection.list;

public class TestConcurrentTask1 {
    public static void main(String[] args) {
        CollectionTask ct = new CollectionTask();

        for (int i = 0; i < 100; i++) {
            new Thread(ct).start();
        }
    }
}
