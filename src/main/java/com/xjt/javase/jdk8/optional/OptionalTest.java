package com.xjt.javase.jdk8.optional;

import com.xjt.javase.jdk8.Employee;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(new Employee());
        Employee emp = op.get();
        System.out.println(emp);
    }

    @Test
    public void test2() {
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }

    @Test
    public void test3() {
//        Optional<Employee> op = Optional.ofNullable(new Employee());
        Optional<Employee> op = Optional.ofNullable(null); //不能传null
        if (op.isPresent()) {
            System.out.println(op.get());
        }
//        System.out.println("-------------------------------------------------------");
//        Employee emp = op.orElse(new Employee("zhangsan", 18, 888.88));
//        System.out.println(emp);
//        System.out.println("-------------------------------------------------------");
//        Employee emp1 = op.orElseGet(() -> new Employee());
//        System.out.println(emp1);
    }

    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee("zhangsan", 18, 888.88));
//        Optional<String> s = op.map((e) -> e.getName());
//        System.out.println(s.get());
        //多一步判空
        Optional<String> s = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(s.get());

    }
}
