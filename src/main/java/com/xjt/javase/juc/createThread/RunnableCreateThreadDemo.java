package com.xjt.javase.juc.createThread;

public class RunnableCreateThreadDemo {
    public static void main(String[] args) {
        MyRunnale mr = new MyRunnale();
        Thread thread = new Thread(mr);
        thread.start();
    }
}

//1.定义一个类实现Runnable
class MyRunnale implements Runnable{
    //2.重写run方法
    @Override
    public void run() {
        //3.将要执行的代码写在run方法中
        for (int i = 0; i < 1000; i++) {
            System.out.println("我是线程"+i);
        }
    }
}
