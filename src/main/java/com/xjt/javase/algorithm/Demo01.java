package com.xjt.javase.algorithm;

import org.junit.Test;

import java.util.*;

public class Demo01 {
    //替换空格
    @Test
    public void test01(){
        String s = "We are happy.";
        String s1 = s.replaceAll(" ", "%20");
        System.out.println(s);      //We are happy.
        System.out.println(s1);     //We%20are%20happy.
    }

    @Test
    public void test02(){
        ArrayList<String> list = new ArrayList<>();
        //添加元素
        list.add("hello");
        list.add("PHP");
        list.add("Java");

        //获取迭代器
        Iterator<String> it = list.iterator();
        //遍历集合
        while (it.hasNext()) {
            String s = it.next();
            if(s.equals("PHP")) {
                it.remove();
            }
        }
        System.out.println(list);
    }

    @Test
    public void test03(){
        //创建集合对象
        List<String> list = new ArrayList<String>();
        //添加元素
        list.add("hello");
        list.add("PHP");
        list.add("Java");
        System.out.println("判断之前集合的元素: "+list);

        //需求:如果集合中没有JavaSE该元素,请添加一个JavaSE元素
        //解决方式一:循环遍历集合,判断集合是否包含JavaSE,如果没有包含就调用集合的add方法进行添加操作
        //解决方式二:使用集合contains方法判断,根据判断的结果决定是否要添加元素
        if(!list.contains("JavaSE")){
            list.add("JavaSE");
        }
        System.out.println("判断之后集合的元素: "+list);
    }

    @Test
    public void test04(){
        //创建集合对象
        List<String> list = new ArrayList<String>();
        //添加元素
        list.add("hello");
        list.add("PHP");
        list.add("Java");

        long startTime = System.currentTimeMillis();
        //需求:还需要添加10W条数据
        for (int i = 0; i < 100000; i++) {
            list.add(i+"");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("未指定容量: "+ (endTime - startTime));

        //创建集合的时候指定足够大的容量
        List<String> list1 = new ArrayList<String>(100000);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list1.add(i+"");
        }
        endTime = System.currentTimeMillis();
        System.out.println("指定容量: "+ (endTime - startTime));
    }

    @Test
    public void test05(){
        //创建ArrayList集合对象
        ArrayList<String> arrayList = new ArrayList<String>();
        //添加500W个元素
        for (int i = 0; i < 5000000; i++) {
            arrayList.add(i+"列表");
        }
        //获取开始时间
        long startTime = System.currentTimeMillis();
        //根据索引删除ArrayList集合元素
        //删除索引5000对应的元素
        String value = arrayList.remove(50000);
        System.out.println(value);
        //获取结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("ArrayList集合删除元素的时间: "+(endTime-startTime));

        //创建LinkedList集合对象
        LinkedList<String> linkedList = new LinkedList<String>();
        //添加500W个元素
        for (int i = 0; i < 5000000; i++) {
            linkedList.add(i+"列表");
        }
        //获取开始时间
        startTime = System.currentTimeMillis();
        //根据索引删除LinkedList集合元素
        //删除索引5000对应的元素
        value = arrayList.remove(50000);
        System.out.println(value);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList集合删除元素的时间: "+(endTime-startTime));
    }


    @Test
    public void test06(){
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            new Thread(() ->{
                strings.add(Thread.currentThread().getName());
            },"t" +i).start();
        }

        System.out.println(strings);
    }

    @Test
    public void test07(){
        List<String> strings = new Vector<String>();

        for (int i = 0; i < 50; i++) {
            new Thread(() ->{
                strings.add(Thread.currentThread().getName());
            },"t" +i).start();
        }

        System.out.println(strings);
    }


}
