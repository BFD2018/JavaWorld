package com.xjt.javase.algorithm.dynamic;

import java.util.Arrays;

public class KnapsackProblem {
    public static void main(String[] args) {
        //吉他-1kg-$1500  音响-4kg-$3000  电脑-3kg-$2000
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 4;  //背包容量4kg
        int n = val.length;     //物品个数

        //创建二维数组
        //v[i][j] 表示前i个物品中能够装入容量为j的背包的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品的情况 定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行 第一列 置0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;        //第一列置0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;    //第一行置0
        }

        System.out.println("初始化之后的数组=======>");
        for (int[] row : v) {
            System.out.println(Arrays.toString(row));
        }

        //按照分析的公式 填表（注意i j 从1开始的）
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i - 1][j], (val[i - 1] + v[i - 1][j - w[i - 1]]));
                    //为了记录商品存放到背包的情况 我们不能直接的使用上面的公式，需要使用if-else来体现公式
                    if (v[i - 1][j] < (val[i - 1] + v[i - 1][j - w[i - 1]])) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //打印结果
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.printf(v[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("============================");
        //输出最后我们是放入的哪些商品
        //遍历path, 这样输出会把所有的放入情况都得到, 其实我们只需要最后的放入
//		for(int i = 0; i < path.length; i++) {
//			for(int j=0; j < path[i].length; j++) {
//				if(path[i][j] == 1) {
//					System.out.printf("第%d个商品放入到背包\n", i);
//				}
//			}
//		}

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
