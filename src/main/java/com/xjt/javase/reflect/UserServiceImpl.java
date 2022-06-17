package com.xjt.javase.reflect;

public class UserServiceImpl implements UserService {

    @Override
    public Person getUserByName() {
        Person person = new Person("xiong",18);
        System.out.println(person);
        return person;
    }

    @Xxoo
    public void testxxoo() {
        Person person = new Person("xiong",18);
        System.out.println(person);
    }
}
