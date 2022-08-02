package com.xjt.javase.algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> set1 = new HashSet<>();
        set1.addAll(Arrays.asList("北京", "上海", "天津"));
        HashSet<String> set2 = new HashSet<>();
        set2.addAll(Arrays.asList("北京", "广州", "深圳"));
        HashSet<String> set3 = new HashSet<>();
        set3.addAll(Arrays.asList("成都", "上海", "杭州"));
        HashSet<String> set4 = new HashSet<>();
        set4.addAll(Arrays.asList("上海", "天津"));
        HashSet<String> set5 = new HashSet<>();
        set5.addAll(Arrays.asList("杭州", "大连"));
        broadcasts.put("K1", set1);      //电台K1 覆盖set1地区
        broadcasts.put("K2", set2);
        broadcasts.put("K3", set3);
        broadcasts.put("K4", set4);
        broadcasts.put("K5", set5);

        //allAreas 存放所有的地区  {"北京", "上海", "天津","广州",  "深圳","成都", "杭州","大连"}
        HashSet<String> allAreas = new HashSet<>();
        allAreas.addAll(set1);
        allAreas.addAll(set2);
        allAreas.addAll(set3);
        allAreas.addAll(set4);
        allAreas.addAll(set5);
        System.out.println("allAreas===>" + allAreas);
        System.out.println("broadcasts--->" + broadcasts);

        //存放所有的电台集合（这些电台加起来能覆盖所有地区）
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时集合，存放遍历过程中的电台覆盖的地区和当前还没有覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义一个maxKey 保存一次遍历过程中，能够覆盖 最大未覆盖地区 对应的电台key
        //如果maxKey不为null 就加入到selects
        String maxKey = null;

        //从分析思路中得知 allAreas 中元素逐渐减少 直至为0 时 就覆盖了所有地区
        while (allAreas.size() > 0) {
            //每次清洗allAreas集合后 都要重置maxKey
            maxKey = null;

            for (String key : broadcasts.keySet()) {
                //对所有电台遍历 每次进来都要重置tempSet
                tempSet.clear();

                //当前电台key能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas 的交集 把交集结果赋值给tempSet
                tempSet.retainAll(allAreas);

                //如果当前这个集合包含的未覆盖地区比maxKey指向的集合地区还多就重置maxKey
                // tempSet.size() >broadcasts.get(maxKey).size()) 体现出贪心算法的特点:每次都选择最优的
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的电台覆盖地区 从allAreas中移除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("贪心算法计算得到的结果是：" + selects);
    }
}
