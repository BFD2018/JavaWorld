package com.xjt.javase.juc.atguigu2022.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("=============>"+ Thread.currentThread().getName() +" thread");
            try {
                TimeUnit.SECONDS.sleep(2);      //执行需要2秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        System.out.println("=============>main thread");
        try {
            TimeUnit.SECONDS.sleep(3);//等待需要1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(uCompletableFuture.getNow("xxx"));      //执2-等1 返回xxx

//        System.out.println(uCompletableFuture.complete("completeValue")+"\t"+uCompletableFuture.get());//执2-等1 返回true+备胎值completeValue
    }
}

