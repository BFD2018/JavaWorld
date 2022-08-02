package com.xjt.javase.oop.innerClass;

public class InnerClassExercise02 {
    public static void main(String[] args) {
        /*
            1.有一个铃声接口 Bell，里面有个 ring 方法。
            2.有一个手机类 Cellphone，具有闹钟功能 alarmClock，参数是 Bell 类型(右图)
            3.测试手机类的闹钟功能，通过匿名内部类(对象)作为参数，打印：懒猪起床了
            4.再传入另一个匿名内部类(对象)，打印：小伙伴上课了
        */
        Cellphone cellphone = new Cellphone();
        cellphone.alarmClock(new Bell() {
            @Override
            public void ring() {
                System.out.println("懒猪起床了");
            }
        });

        cellphone.alarmClock(new Bell() {
            @Override
            public void ring() {
                System.out.println("小伙伴上课了");
            }
        });
    }
}

interface Bell{
    void ring();
}

class Cellphone{
    public void alarmClock(Bell bell){
        System.out.println(bell.getClass());
        bell.ring();        //动态绑定
    }
}
