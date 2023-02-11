package com.xjt.javase.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor         //全参构造
@NoArgsConstructor      //无参构造
public class Person {
    private String name;
    private Integer age;

    private Person(String name) {
        this.name = name;
    }

    public Person(Integer age) {
        this.age = age;
    }

    public void doRun(){
        System.out.println("跑步...");
    }

    public void doSay(String[] hobbies){
        System.out.println("Person的名字name:"+name + "  年龄age:"+age + " 喜欢："+hobbies);
    }

    private void doThink(){
        System.out.println("思考...");
    }
}
