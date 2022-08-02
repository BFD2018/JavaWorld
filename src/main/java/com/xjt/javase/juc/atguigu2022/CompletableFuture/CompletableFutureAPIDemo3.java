package com.xjt.javase.juc.atguigu2022.CompletableFuture;

import java.util.concurrent.*;

//2-1
public class CompletableFutureAPIDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
            return "abcd";
        }, threadPool).thenRun(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
        }).thenRun(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
        }).thenRun(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
        });
    }
}
//1号任务	pool-1-thread-1
//2号任务	pool-1-thread-1
//3号任务	pool-1-thread-1
//4号任务	pool-1-thread-1


