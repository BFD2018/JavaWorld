package com.xjt.javase.juc.threadPool;

import java.util.concurrent.*;

public class ExecuteCallableDemo {
    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 50, 300, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(50),
            new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    return new Thread(r, "schema_task_pool_" + r.hashCode());
                }
            }, new ThreadPoolExecutor.DiscardOldestPolicy());

    public static int count = 1;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //callable
            Future<Boolean> future = threadPool.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    count += 1;
                    System.out.println(Thread.currentThread().getName() + " --- " + count);
                    return true;
                }
            });
            try {
                System.out.println("feature.get");
                Boolean boolean1 = future.get();
                System.out.println(boolean1);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException...");
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("execute exception...");
                e.printStackTrace();
            }
        }
    }
}
