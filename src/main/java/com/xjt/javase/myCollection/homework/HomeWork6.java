package com.xjt.javase.myCollection.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Vector;

public class HomeWork6 {
    public static void main(String[] args) {
        HashSet<Object> set = new HashSet<>();
        Student s1 = new Student(1001, "AA");
        Student s2 = new Student(1002, "BB");
        set.add(s1);
        set.add(s2);
        s1.name = "CC";
        set.remove(s1);
        System.out.println(set);

        set.add(new Student(1001,"CC"));
        System.out.println(set);
        set.add(new Student(1001,"AA"));
        System.out.println(set);
    }
}

class Student{
    public Integer id;
    public String name;

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
