package com.xjt.javase.juc.object_model;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class ObjectMemoryModelDemo {
    public static void main(String[] args) {
//        Object o = new Object();
//        System.out.println( ClassLayout.parseInstance(o).toPrintable());

        System.out.println(VM.current().details());
    }

}
