package com.xjt.javase.leetcode.S518;

import java.util.Arrays;

public class WeightedRandom {
    int[] pre;
    int total;

    //input = [1,2,3,4]   pre = [1,3,6,10]
    public WeightedRandom(int[] input) {
        pre = new int[input.length];
        pre[0] = input[0];
        for (int i = 1; i < input.length; ++i) {
            pre[i] = pre[i - 1] + input[i];
        }
        total = Arrays.stream(input).sum();
    }

    public int next() {
        int x = (int) (Math.random() * total) + 1;      //[1,11)
        return binarySearch(x);
    }

    private int binarySearch(int x) {
        int low = 0, high = pre.length - 1;  //0 3
        while (low < high) {
            int mid = (high - low) / 2 + low;   //1
            if (pre[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}

