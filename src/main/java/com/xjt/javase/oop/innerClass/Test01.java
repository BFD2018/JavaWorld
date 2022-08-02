package com.xjt.javase.oop.innerClass;

public class Test01 {
    public Test01() {//构造器
        Inner s1 = new Inner();
        s1.a = 10;
        Inner s2 = new Inner();
        System.out.println(s2.a);   //5
    }
    class Inner { //内部类，成员内部类
        public int a = 5;
    }
    public static void main(String[] args) {
        Test01 t = new Test01();
        Inner r = t.new Inner();
        System.out.println(r.a);    //5
    }
}
