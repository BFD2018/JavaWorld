package com.xjt.javase.algorithm.array;

import java.util.Arrays;

public class MoveZeros {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);

        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        int idx = 0;
        for (int num : nums) {
            if(num != 0){
                nums[idx] = num;
                idx++;
            }
        }

        while(idx < nums.length){
            nums[idx] = 0;
            idx++;
        }
    }

}
