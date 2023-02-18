package com.xjt.javase.my_dataType.num;

import org.junit.Test;

public class NumDemo {
    @Test
    public void testInterger(){
        Integer integer = new Integer(10);
        System.out.println(integer);

        Object b = 42.0;
        if (b instanceof Integer) {
            System.out.println(42 == ((Integer) b).intValue());
        }
    }

    // Interger 可以用 == 比较吗？
    @Test
    public void testInterger02(){
        Integer a = 100;
        Integer b = 100;
        Integer c = 128;
        Integer d = 128;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(c == d);     //false
        System.out.println(c.equals(d));    //true

        Integer e = -129;
        Integer f = -129;
        System.out.println(e ==f);      //false
        System.out.println(e.equals(f));        //true
    }

}
