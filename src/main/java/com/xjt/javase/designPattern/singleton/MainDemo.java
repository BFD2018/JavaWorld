package com.xjt.javase.designPattern.singleton;

public class MainDemo {
    public static void main(String[] args) {
//        Singleton instance = Singleton.getInstance();
//        Singleton instance2 = Singleton.getInstance();
//        System.out.println(instance == instance2);


        //测试 3-饿汉式 线程不安全
//        for (int i = 0; i < 100; i++) {
//            new Thread(() ->{
//                Singleton3 instance = Singleton3.getInstance();
//                System.out.println(Thread.currentThread().getName() + " --- " + instance);
//            }).start();
//        }

        //测试 4-饿汉式 (同步方法)线程安全
//        for (int i = 0; i < 100; i++) {
//            new Thread(() ->{
//                Singleton4 instance = Singleton4.getInstance();
//                System.out.println(Thread.currentThread().getName() + " --- " + instance);
//            }).start();
//        }

        //测试 5-饿汉式 (同步代码块)线程安全
//        for (int i = 0; i < 100; i++) {
//            new Thread(() ->{
//                Singleton5 instance = Singleton5.getInstance();
//                System.out.println(Thread.currentThread().getName() + " --- " + instance);
//            }).start();
//        }
            // 测试 7
//        for (int i = 0; i < 100; i++) {
//            new Thread(() ->{
//                Singleton7 instance = Singleton7.getInstance();
//                System.out.println(Thread.currentThread().getName() + " --- " + instance);
//            }).start();
//        }

        // 测试 枚举
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                Singleton8 instance = Singleton8.INSTANCE;
                System.out.println(Thread.currentThread().getName() + " --- " + instance.hashCode());
            }).start();
        }
    }

}

//1、饿汉式
class Singleton {
    //1. 构造器私有化, 外部能 new
    private Singleton() {
    }

    //2.内部new一个私有的静态对象实例
    private static Singleton obj = new Singleton();

    //3.对外暴露一个静态的获取内部对象的方法
    public static Singleton getInstance(){
        return obj;
    }
}

//2、饿汉式（还有另一种写法 就是将new对象过程放在静态代码块中实现）
class Singleton2 {
    //1. 构造器私有化, 外部能 new
    private Singleton2() {
    }

    //2.内部new一个私有的静态对象实例
    private static Singleton2 obj;

    static {
        obj = new Singleton2();
    }

    //3.对外暴露一个静态的获取内部对象的方法
    public static Singleton2 getInstance(){
        return obj;
    }
}

//3、懒汉式（在获取对象方法中判断是否有instance对象，这种方式是线程不安全的）
class Singleton3 {
    //1. 构造器私有化, 外部能 new
    private Singleton3() {
    }

    //2.内部new一个私有的静态对象实例
    private static Singleton3 instance;

    //3.对外暴露一个静态的获取内部对象的方法
    public static Singleton3 getInstance(){
        if(instance == null){
            instance = new Singleton3();
        }
        return instance;
    }
}

//4、懒汉式（在getInstance方法上加上synchronized 同步方法（每次只能有一个线程进入））
class Singleton4 {
    //1. 构造器私有化, 外部能 new
    private Singleton4() {
    }

    //2.内部new一个私有的静态对象实例
    private static Singleton4 instance;

    //3.对外暴露一个静态的获取内部对象的方法
    public static synchronized Singleton4 getInstance(){
        if(instance == null){
            instance = new Singleton4();
        }
        return instance;
    }
}

//5、懒汉式（在getInstance方法中创建对象外添加同步代码块）
class Singleton5 {
    //1. 构造器私有化, 外部能 new
    private Singleton5() {
    }

    //2.内部new一个私有的静态对象实例
    private static Singleton5 instance;

    //3.对外暴露一个静态的获取内部对象的方法
    public static Singleton5 getInstance(){
        if(instance == null){

            synchronized (Singleton5.class){
                instance = new Singleton5();
            }
        }
        return instance;
    }
}

//6 双重检查
class Singleton6 {
    //1. 构造器私有化, 外部能 new
    private Singleton6() {
    }

    //2.内部new一个私有的静态对象实例
    private static  volatile Singleton6 instance;

    //3.对外暴露一个静态的获取内部对象的方法
    public static Singleton6 getInstance(){
        if(instance == null){
            synchronized (Singleton6.class){
                if(instance == null){
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}

//7、静态内部类
class Singleton7 {
    private Singleton7 (){}

    private static class SingletonHolder {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static final Singleton7 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

//8、枚举
enum Singleton8{
    INSTANCE;
    public void sayOK(){
        System.out.println("ok-----");
    }
}