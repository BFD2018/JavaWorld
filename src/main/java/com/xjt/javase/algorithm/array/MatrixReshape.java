package com.xjt.javase.algorithm.array;

public class MatrixReshape {
    public static void main(String[] args) {

    }
    /* m=2  n=2  r=1  c=4
    * index=0 nums[0][0]    reshapedNums[0][0]
    * index=1 nums[0][1]    reshapedNums[0][1]
    * index=2 nums[1][0]    reshapedNums[0][2]
    * index=3 nums[1][1]    reshapedNums[0][3]
    * */
    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) {
            return nums;
        }
        int[][] reshapedNums = new int[r][c];
        int index = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                reshapedNums[i][j] = nums[index / n][index % n];
                index++;
            }
        }
        return reshapedNums;
    }

}
