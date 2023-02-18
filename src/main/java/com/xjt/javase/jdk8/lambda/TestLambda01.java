package com.xjt.javase.jdk8.lambda;

import com.xjt.javase.jdk8.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 学习Lambda表达式
 */
public class TestLambda01 {
    Employee employee1 = new Employee("张三", 18, 3999.33);
    Employee employee2 = new Employee("李四", 28, 2999.33);
    Employee employee3 = new Employee("王五", 44, 5999.33);
    Employee employee4 = new Employee("赵六", 12, 1999.33);
    Employee employee5 = new Employee("田七", 56, 8999.33);

    List<Employee> list = Arrays.asList(employee1, employee2, employee3, employee4, employee5);

    /**
     * 使用匿名内部类过滤出 年龄>30 薪水>5000 的所有员工
     */
    @Test
    public void testAnonymousInnerClass(){
    }

    public List<Employee> filterEmployee(List<Employee> employeeList,Predicate<Employee> predicate){
        return null;
    }

    @Test
    public void test01(){
        list.stream()
                .filter(employee -> employee.getAge() > 30)
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
        Consumer<String> consumer1 = s -> {
            s+=" xiong";
            System.out.println("s="+s);
        };
        Consumer<String> consumer2 = s -> {
            s+=" world";        //接收的s还是accept传入的参数
            System.out.println("s="+s);
        };
        consumer1.andThen(consumer2).accept("Justin");
    }

}
