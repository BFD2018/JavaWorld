package com.xjt.javase.algorithm.dynamic;

public class HouseRobber {
    public static void main(String[] args) {
//        int[] nums = {1,2};
        int[] nums = {3,2,3,4,8,5};
//        int[] nums = {1,2,3};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if(len == 1){
            return nums[0];
        }
        //最大价值,使用数组存储结果
        int[] dp = new int[len];
        dp[0] = nums[0];    //nums只有一个元素
        dp[1] = Math.max(nums[0],nums[1]);  //nums有2个元素

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }

        return dp[len - 1];
    }
}
