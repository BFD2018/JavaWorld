package com.xjt.javase.algorithm.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMaxConsecutiveOnes {
    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};
        System.out.println(myFindMaxConsecutiveOnes(nums));
    }

    /*
    * 输入：nums = [1,1,0,1,1,1]
        输出：3
    * */
    public static int myFindMaxConsecutiveOnes(int[] nums) {
        List<Integer> oneNums = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int n = 0;
            while (i < nums.length && nums[i] == 1){
                if(nums[i++] != 1){
                    break;
                }
                n++;
            }

            oneNums.add(n);
        }

        return Collections.max(oneNums);
    }

    /*
    *  作者：LeetCode-Solution
    链接：https://leetcode.cn/problems/max-consecutive-ones/solution/zui-da-lian-xu-1de-ge-shu-by-leetcode-so-252a/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    * */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(maxCount, count);
        return maxCount;
    }


}
