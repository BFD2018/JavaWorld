package com.xjt.javase.myCollection.set;

import java.util.HashSet;
import java.util.Set;

public class SetDemo1 {
    public static void main(String[] args) {
        //你真的了解add方法吗？
        Set hashSet = new HashSet();
        hashSet.add("jack");
        hashSet.add("lucy");
        hashSet.add("justin");
        hashSet.add(null);
        hashSet.add(null);
        hashSet.add("jack");
        System.out.println(hashSet);        //[null, justin, lucy, jack]

        hashSet = new HashSet();

        System.out.println(hashSet);        // []

        hashSet.add("alex");
        hashSet.add("jack");
        hashSet.add("lucy");
        hashSet.add(new Dog("tom"));
        hashSet.add(new Dog("tom"));
        hashSet.add(new String("david"));
        hashSet.add(new String("david"));
        System.out.println(hashSet);    //  [alex, david, Dog{name='tom'}, lucy, jack, Dog{name='tom'}]
    }
}

class Dog{
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
