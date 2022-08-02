package com.xjt.javase.algorithm.dynamic;

public class ClimbStairsDemo {
    public static void main(String[] args) {
        System.out.println(climbstairs(2));
        System.out.println(climbstairs(3));
        System.out.println(climbstairs(4));
    }

    /**
     *
     * @param n 楼梯高度
     * @return
     */
    public static int climbstairs(int n){
        if(n<=2){
            return n;
        }
        int pre1 = 1;
        int pre2 = 2;
        for (int i = 2; i < n; i++) {
            int cur = pre1 + pre2;
            pre1 = pre2;
            pre2 = cur;
        }

        return pre2;
    }
}
