package com.xjt.javase.reflect;

public class Book1 {
    // static 作用域方法
    static void staticMethod() {
        System.out.println("执行staticMethod()方法");
    }
    // public 作用域方法
    public int publicMethod(int i) {
        System.out.println("执行publicMethod()方法");
        return 100 + i;
    }
    // protected 作用域方法
    protected int protectedMethod(String s, int i) throws NumberFormatException {
        System.out.println("执行protectedMethod()方法");
        return Integer.valueOf(s) + i;
    }
    // private 作用域方法
    private String privateMethod(String... strings) {
        System.out.println("执行privateMethod()方法");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < sb.length(); i++) {
            sb.append(strings[i]);
        }
        return sb.toString();
    }
}
