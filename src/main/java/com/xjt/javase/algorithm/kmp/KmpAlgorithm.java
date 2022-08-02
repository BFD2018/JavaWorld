package com.xjt.javase.algorithm.kmp;

import java.util.Arrays;

public class KmpAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";     //[A,AB,ABC,ABCD,ABCDA,ABCDAB,BCDABD,CDABD,DABD,ABD,BD,D]
//        String str2 = "BBC";        //[B,BB,BC,C]
        int[] next = kmpNext("ABCDABD");    //[0, 0, 0, 0, 1, 2, 0]
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index);
    }

    public static int kmpSearch(String s1, String s2, int[] next) {
        for (int i = 0, j = 0; i < s1.length(); i++) {
            //需要处理s1.charAt(i) != s2.charAt(j) 的时候 调整j的大小
            //KMP算法的核心点！！！
            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                j = next[j - 1];
            }

            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {   //找到了
                return i - (j - 1);
            }
        }

        return -1;
    }

    //获取子串的部分匹配表
    public static int[] kmpNext(String dest) {
        //创建一个数组next[] 保存部分匹配值
        int[] next = new int[dest.length()];

        //如果是一个字符串（即字符串长度为1 部分匹配值是0）
        next[0] = 0;

        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) 我们需要从next[j-1]获取新的j，
            //直到我们发现 j=0 或者 dest.charAt(i) == dest.charAt(j)才退出
            //这是KMP算法的核心点！！！
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            //当 dest.charAt(i) == dest.charAt(j) 满足时 部分匹配值+1
            //举例： AA[A,A]  ABA[A,AB,BA,A]
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }
}
