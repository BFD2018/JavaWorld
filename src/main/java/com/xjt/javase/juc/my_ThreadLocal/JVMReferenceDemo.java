package com.xjt.javase.juc.my_ThreadLocal;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;

class MyObject{
    //一般我们不要重写这个方法 它是给虚拟机开发人员用的
    @Override
    protected void finalize() throws Throwable {
        System.out.println("--------------->invoke finalize");
    }
}
public class JVMReferenceDemo {
    public static void main(String[] args) {


//        m_noSoftReference();

//        m_softReference2();

//        m_weakReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(null,111);
        hashMap.put(null,"china");
        hashMap.put(null,new Date());
        hashMap.put(null,200L);
    }

    public static void m_weakReference() {
        MyObject myObject = new MyObject();
        WeakReference<MyObject> weakReference = new WeakReference<>(myObject);
        System.out.println("gc 回收之前...." + weakReference.get());
        myObject = null;
        System.gc();        //手动调用垃圾回收期
        System.out.println("gc 回收之后...." + weakReference.get());
    }

    /**
     * 手动设置IDEA的虚拟机参数：-Xms10m -Xmx10m
     * 创建一个20MB数组 撑爆内存
     */
    public static void m_softReference2() {
        MyObject myObject = new MyObject();
        SoftReference<MyObject> softReference = new SoftReference<>(myObject);

        System.out.println("gc 回收之前...." + softReference.get());
        System.gc();        //手动调用垃圾回收期
        try {
            byte[] bytes = new byte[10 * 1024 * 1024];      //20MB
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("gc 回收之后...." + softReference.get());
        }

    }

    public static void m_noSoftReference() {
        MyObject myObject = new MyObject();
        SoftReference<MyObject> softReference = new SoftReference<>(myObject);

        System.out.println("gc 回收之前...." + softReference.get());

        System.gc();        //手动调用垃圾回收期
        System.out.println("gc 回收之后...." + softReference.get());

//        gc 回收之前....com.xjt.javase.juc.my_ThreadLocal.MyObject@1e80bfe8
//        gc 回收之后....com.xjt.javase.juc.my_ThreadLocal.MyObject@1e80bfe8
    }
}
