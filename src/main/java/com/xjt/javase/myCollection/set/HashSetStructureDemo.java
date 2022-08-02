package com.xjt.javase.myCollection.set;

import javax.swing.*;

/**
 * 模拟HashMap底层 数据结构
 */
public class HashSetStructureDemo {
    public static void main(String[] args) {
        //1.创建一个数组 存放Node 也就是表
        Node[] table = new Node[16];

        //创建节点
        Node john = new Node("John", null);
        Node Jack = new Node("Jack", null);
        Node Lucy = new Node("Lucy", null);
        Node Tom = new Node("Tom", null);
        Node Jerry = new Node("Jerry", null);
        Node Mary = new Node("Mary", null);
        table[0] = john;
        john.next = Jack;
        Jack.next = Lucy;
        table[3] = Tom;
        Tom.next = Jerry;
        table[5] = Mary;

        System.out.println(table);
    }
}

class Node{
    public Object item; //存放元素
    public Node next;   //指向下一个节点

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }


    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", next=" + next +
                '}';
    }
}
