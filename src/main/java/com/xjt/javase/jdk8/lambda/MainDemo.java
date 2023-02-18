package com.xjt.javase.jdk8.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class MainDemo {
    private static Comparator<String> newComparator() {		//返回一个比较器类型
        return (a,b) -> b.length() - a.length();
    }

    public static void main(String[] args) {
        String[] array = {"abc", "ab", "abcd"};
        System.out.println(Arrays.toString(array));
        Arrays.sort(array, newComparator());
        System.out.println(Arrays.toString(array));
    }

}
