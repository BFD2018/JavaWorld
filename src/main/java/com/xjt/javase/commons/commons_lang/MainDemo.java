package com.xjt.javase.commons.commons_lang;


import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2023/7/23 11:17
 */
public class MainDemo {
    public static void main(String[] args) {


    }

    @Test
    public  void testStringUtils(){
        // 1.判断字符串为空
        String name = " 123 xiong ";
        if(!StringUtils.isEmpty(name)){
            System.out.println(name);
        }
        String email = null;
        if(!StringUtils.isEmpty(email)){
            System.out.println("email is not empty");
        }


        //2.拼接字符数组
        String[] names = {"zhangsan", "lisi", "wangwu", "zhaoliu"};
        String join = StringUtils.join(names, ",");
        System.out.println(join);


        //3. 判断两个未知的字符串是否相等(不会报空指针异常)
        String a = "shenzhen";
        String b = null;
        if(StringUtils.equals(a,b)){
            System.out.println("a == b");
        }
    }

    @Test
    public  void testNumberUtils(){
        int compare = NumberUtils.compare(1.3f, 2.6f);
        System.out.println(compare);

        int[] intarray = new int[]{1,5,3,9,26,34,68,0,2,4,12};
        int max = NumberUtils.max(intarray);
        System.out.println("max array=" + max);

        byte a = NumberUtils.toByte("a");
        System.out.println(a);
    }

    @Test
    public void testArrayUtils(){
        String str = "hello shenzhen";
        byte[] bytes = str.getBytes();
        byte[] clone = ArrayUtils.clone(bytes);
        System.out.println(new String(clone));


    }

    @Test
    public void testSystemUtils(){
        String userName = SystemUtils.getUserName();

        File javaHome = SystemUtils.getJavaHome();

        File userHome = SystemUtils.getUserHome();

        String javaHome1 = SystemUtils.JAVA_HOME;


    }

    @Test
    public void testRandomUtils(){
        boolean b = RandomUtils.nextBoolean();

        int i = RandomUtils.nextInt();

        Random jvmRandom = RandomUtils.JVM_RANDOM;

        long l = RandomUtils.nextLong();
    }

    @Test
    public void testDateUtils(){

    }

    /**
     * @description: 反射
     * @author jtxiong
     * @date 2023/7/23 11:46
     * @version 1.0
     */
    @Test
    public void testMethodUtils() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // java反射实现方法调用
        Object max = Integer.class.getMethod("max", int.class, int.class).invoke(null, 1, 2);
        System.err.println(max);

        // MethodUtils.invokeMethod实现方法调用
        Object max1 = MethodUtils.invokeStaticMethod(Integer.class, "max", new int[]{1, 2});
        System.err.println();

    }
}
