package com.xjt.javase.test.equals;

import lombok.Data;

import java.util.Objects;

@Data
class Animal{
    private String name;
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Animal animal = (Animal) o;
        return name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class Dog extends Animal{

}

class Cat extends Animal{

}

public class EqualsDemo {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.setName("xiaoqiang");
        dog.setAge(22);
        Animal dog2 = new Dog();
        dog2.setName("xiaoqiang");
        dog2.setAge(18);

        Animal cat = new Cat();
        cat.setName("xiaoqiang");

        System.out.println(dog.equals(dog2));       //true
        System.out.println(dog.equals(cat));        //true
    }
}
