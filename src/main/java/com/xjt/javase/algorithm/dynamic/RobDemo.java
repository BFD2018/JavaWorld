package com.xjt.javase.algorithm.dynamic;

import cn.hutool.extra.mail.MailAccount;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。

 输入：nums = [2,3,2]
 输出：3
 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。

 输入：nums = [1,2,3,1]
 输出：4
 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
      偷窃到的最高金额 = 1 + 3 = 4 。

 输入：nums = [1,2,3]
 输出：3
 */
public class RobDemo {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums){
        int length = nums.length;
        if(length == 0|| nums == null){
            return 0;
        }
        if(length == 1){
            return nums[0];
        }
        //环形的看成直线的话，可以理解成去掉最有一个元素，或者去掉第一个元素，然后找到这两种场景的最大值
        // 1）去掉nums[0]
        int[] dp1 = new int[nums.length-1];
        // 2)去掉nums[nums.length-1]
        int[] dp2 = new int[nums.length-1];

        for (int i = 0; i < length - 1; i++) {
            dp1[i] = nums[i];
            dp2[i] = nums[i+1];
        }

        return Math.max(inRob(dp1),inRob(dp2));
    }

    public static int inRob(int[] nums){
        int length = nums.length;
        if(length == 0|| nums == null){
            return 0;
        }
        if(length == 1){
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
        }

        return dp[length-1];
    }
}
