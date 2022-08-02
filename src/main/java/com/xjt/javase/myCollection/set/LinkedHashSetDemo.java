package com.xjt.javase.myCollection.set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        Set<Object> set = new LinkedHashSet<>();
        set.add(new String("ABC"));
        set.add(123);
        set.add(123);
        set.add(new Cat("tom",3));
        set.add(456);
        set.add("java");
        System.out.println(set);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Cat{
    public String name;
    public int age;
}
