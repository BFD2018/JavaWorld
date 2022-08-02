package com.xjt.javase.myCollection.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

public class HomeWork2 {
    public static void main(String[] args) {
        ArrayList<Car> list = new ArrayList<>();
        Car car = new Car("宝马", 400000);
        Car car2 = new Car("宾利", 5000000);
        list.add(car);
        list.add(car2);

        //list.remove(0);     //删除索引为0 的元素
        //list.remove(car);       //删除指定元素

        boolean has = list.contains(car);
        int size = list.size();
        boolean empty = list.isEmpty();
        list.clear();


        boolean b = list.addAll(Arrays.asList(car, car2));

        //遍历
        for (Car c: list) {
            System.out.println(c);
        }

        System.out.println("迭代器遍历------------>");
        Iterator<Car> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Car{
    private String name;
    private Integer price;
}
