package com.xjt.javase.leetcode.math;

import cn.hutool.extra.mail.MailUtil;
import org.junit.Test;

import java.util.Arrays;

//Math类的用法
public class randomDemo {

    // Math.random() 等概率的返回 [0,1) 之间的数
    @Test
    public void test01(){
        for (int i = 0; i < 20; i++) {
            double random = Math.random();
            System.out.println(random);
        }
    }

    //证明等概率返回[0,1) 之间的数
    @Test
    public void test02(){
        int nums = 10000000;
        int count = 0;
        for (int i = 0; i < nums; i++) {
            double random = Math.random();
            if(random < 0.3){
                count++;
            }
        }
        double prob = (double) count / (double) nums;
        System.out.println(prob);   //0.2999668
    }

    //要求：等概率返回[1,9] 之间整数
    // [0,1) * 10 = [0,10)   取整---> 0,1,2,3,4,5,6,7,8,9
    @Test
    public void test03(){
        int[] counts = new int[10];
        for (int j = 0; j < 1000000; j++) {
            int i = (int) (Math.random() * 10);
            counts[i]++;
        }

        System.out.println(Arrays.toString(counts));    //[100625, 100492, 99854, 100554, 99784, 99692, 99883, 99413, 99842, 99861]
    }

    /**
     * 需求：要求返回 [0,x) 之间数的概率是 1/x^2
     */
    @Test
    public void test04(){
        int count = 0;
        for (int j = 0; j < 1000000; j++) {
            double v = Math.random() * Math.random() * 5;
            if(v < 1){
                count++;
            }
        }

        System.out.println(count);
    }
}
