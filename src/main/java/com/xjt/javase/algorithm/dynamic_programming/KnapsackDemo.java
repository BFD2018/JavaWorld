package com.xjt.javase.algorithm.dynamic_programming;

/**
 * 0-1背包问题
 */
public class KnapsackDemo {
    public static void main(String[] args) {
        int w[] = {1,4,3};      //物品的重量
        int val[] = {1500,3000,2000};   //物品的价值（与物品的重量 w[] 一一对应）
        int m = 4;  //背包容量
        int n = w.length;   //物品个数

        //创建二维数组表示 在前i个物品中能装入容量为j的背包中的最大价值
        int v[][] = new int[n+1][m+1];
        //为了记录放入商品的情况
        int path[][] = new int[n+1][m+1];

        //初始化 第一行 第一列 默认是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;    //第一列置0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;    //第一行置0
        }

        //
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if(w[i-1] > j){
                    v[i][j] = v[i-1][j];
                }else{
                    //i从1开始的
                    if(v[i-1][j] < (val[i-1] + v[i-1][j-w[i-1]])){
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        //输出一下 v 看看目前的情况
        for(int i =0; i < v.length;i++) {
            for(int j = 0; j < v[i].length;j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("============================");
        //输出最后我们是放入的哪些商品
        //遍历 path, 这样输出会把所有的放入情况都得到, 其实我们只需要最后的放入
    }
}
