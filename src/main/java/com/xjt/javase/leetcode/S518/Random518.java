package com.xjt.javase.leetcode.S518;

public class Random518 {
    public static void main(String[] args) {
        int[] w = {1,3};

        int a = 0,b = 0;
        WeightedRandom solution = new WeightedRandom(w);
        for (int i = 0; i < 1000; i++) {
            if (solution.next() == 0) {
                a+=1;
            }else{
                b+=1;
            }
        }

        System.out.println("a==" + a+ ",b==" + b);
    }
}

