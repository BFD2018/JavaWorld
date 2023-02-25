package com.xjt.javase.juc.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<Student> reference = new AtomicReference<>();
        Student zhang3 = new Student().setName("zhang3").setAge(18);
        Student li4 = new Student().setName("li4").setAge(25);
        System.out.println(reference.compareAndSet(null, zhang3) + "\t" + reference.get());
        System.out.println(reference.compareAndSet(li4, li4) + "\t" + reference.get());
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class Student{
    private String name;
    private Integer age;
}