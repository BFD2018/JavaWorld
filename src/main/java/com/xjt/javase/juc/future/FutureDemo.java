package com.xjt.javase.juc.future;

import lombok.SneakyThrows;

import java.sql.Time;
import java.util.concurrent.*;

public class FutureDemo {
    public Future rice() {
        long start = System.currentTimeMillis();
        try {
            System.out.println("线程：" + Thread.currentThread().getName() + "------> 开始做饭");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + "------> 完成做饭，耗时：" + (System.currentTimeMillis() - start) + " ms");
        return null;
    }

    public Future<String> soup() {
        long start = System.currentTimeMillis();
        try {
            System.out.println("线程：" + Thread.currentThread().getName() + "------> 开始煲汤");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + Thread.currentThread().getName() + "------> 完成煲汤，耗时：" + (System.currentTimeMillis() - start) + " ms");
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

//        m_join();
//        m_future();

        m_futuretask();

        System.out.println("全部准备完毕时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    public static void m_join() throws InterruptedException {
        // JOIN 方法阻塞等待
        Thread rice = new CookRice();
        rice.start();
        rice.join();

        CookSoup soup = new CookSoup();
        soup.start();
        soup.join();
    }

    public static void m_future() throws ExecutionException, InterruptedException {
        // Future 方式非阻塞执行，阻塞等待结果
        Callable callable1 = ()-> {{
            new FutureDemo().rice();
            return "Finish1";
        }
        };

        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        new Thread(futureTask1).start();

        Callable callable2 = ()-> {{
            new FutureDemo().soup();
            return "Finish2";
        }
        };

        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
        new Thread(futureTask2).start();
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
    }

    public static void m_futuretask() throws InterruptedException, ExecutionException {
        FutureTask<Object> task = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("开始做饭--->");
                TimeUnit.MILLISECONDS.sleep(5000);
                return "hello";
            }
        });
        long start = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " 主线程开始工作...time="+start);
        new Thread(task,"t1").start();
        TimeUnit.MILLISECONDS.sleep(1000);

        System.out.println(task.get());     //获取子线程执行结果

        System.out.println(Thread.currentThread().getName() + " 主线程结束工作...耗时==" + (System.currentTimeMillis() - start));
    }

}

class CookRice extends Thread {

    @Override
    public void run() {
        new FutureDemo().rice();
    }
}

class CookSoup extends Thread {
    @Override
    public void run() {
        new FutureDemo().soup();
    }
}