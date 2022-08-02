package com.xjt.javase.algorithm.kmp;

/**
 * 暴力匹配法
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你~";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    public static int violenceMatch(String source, String dest) {
        //将字符串转为字符数组
        char[] sData = source.toCharArray();
        char[] dData = dest.toCharArray();

        int sLen = sData.length;
        int dLen = dData.length;
        int i = 0;  //指向source字符串
        int j = 0;  //指向dest字符串

        while (i < sLen && j < dLen) {
            if (sData[i] == dData[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == dLen) {
            return i - j;
        } else {
            return -1;
        }
    }
}
