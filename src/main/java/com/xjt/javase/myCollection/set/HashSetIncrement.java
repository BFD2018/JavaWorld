package com.xjt.javase.myCollection.set;

import java.util.HashSet;
import java.util.Objects;

public class HashSetIncrement {
    public static void main(String[] args) {
        /*
        HashSet 底层是 HashMap, 第一次添加时，table 数组扩容到 16，
        临界值(threshold)是 16*加载因子(loadFactor)是 0.75 = 12
        如果 table 数组使用到了临界值 12,就会扩容到 16 * 2 = 32,
        新的临界值就是 32*0.75 = 24, 依次类推
         */
        HashSet table = new HashSet();

//        for(int i = 1; i <= 100; i++) {
//            table.add(i);     //1,2,3,4,5...100
//        }
//        System.out.println(table);


        /*在 Java8 中, 如果一条链表的元素个数到达 TREEIFY_THRESHOLD(默认是 8 )，
        并且 table 的大小 >= MIN_TREEIFY_CAPACITY(默认 64),就会进行树化(红黑树),
                否则仍然采用数组扩容机制*/

        for (int i = 0; i < 100; i++) {
            table.add(new A(i));
            if(i>65){
                System.out.println(table);
            }
        }

    }
}

class A{
    public int n;

    public A(int n) {
        this.n = n;
    }

    @Override
    public int hashCode() {
        return 100;
    }
}
