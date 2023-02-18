package com.xjt.javase.jdk8.functionInterface;

import com.xjt.javase.jdk8.Employee;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MainDemo1 {
    public static void main(String[] args) {

    }

    @Test
    public void testSupplier(){
        Supplier<String> sup  = () ->{
            return "美元符号是：$";
        };
        String s = sup.get();
        System.out.println(s);
    }

    @Test
    public void testConsumer(){
        Consumer<Double> con = aDouble -> {
            System.out.println("拿到了钱：" + aDouble);
        };

        con.accept(12.5);
    }

    @Test
    public void testPredicate(){
        Predicate<Employee> pre = (employee -> {
            return employee.getName().length()>2 && employee.getAge() > 30;
        });

        Employee employee = new Employee();
        employee.setName("张三丰");
        employee.setAge(89);
        employee.setSalary(10000.00);
        boolean test = pre.test(employee);
        System.out.println(test);
    }

    /**
     * 将字符串 2023-2-1 12:30:56
     * 转化为 2023年2月1日 12时30分56秒
     */
    @Test
    public void testPredic(){
        Function<String,String> fun = (a -> {
            String[] s = a.split(" ");
            String[] s0 = s[0].split("-");
            String[] s1 = s[1].split(":");
            return s0[0] + "年" + s0[1] + "月" + s0[2] + "日 " + s1[0] + "时" + s1[1] + "分" + s1[2] + "秒";
        });

        String apply = fun.apply("2023-2-1 12:30:56");
        System.out.println(apply);
    }

    // ---------------------------------------练习
    /**
     * 求数组中最大值
     */
    @Test
    public void test01(){
        int[] arr = {2,3,5,9,12,65,32};
        int max1 = getMax(() -> {
            int max = arr[0];
            for (int item : arr) {
                if (max < item) {
                    max = item;
                }
            }

            return max;
        });

        System.out.println(max1);

    }
    public int getMax(Supplier<Integer> supplier){
        return supplier.get();
    }

    /**
     * 定义一个函数式接口IntCalc,其中抽象方法int calc(int a , int b)，使用注解@FunctionalInterface
     * 在测试类中定义void getProduct(int a , int b ,IntCalc intCalc), 该方法的预期行为是使用calc得到a和b的乘积并打印结果
     * 测试getProduct(),通过lambda表达式完成需求
     */
    @Test
    public void test02(){
        getProduct(2,3,(a,b) ->{
            return a * b;
        });
    }

    public void getProduct(int a , int b ,IntCalc intCalc){
        Integer ret = intCalc.calc(a,b);
        System.out.println(ret);
    }

    /**
     * 练习三：静态方法引用
     * 定义一个函数式接口NumberToString,其中抽象方法String convert(int num)，使用注解@FunctionalInterface
     * 在测试类中定义public void decToHex(int num ,NumberToString nts), 该方法的预期行为是使用nts将一个十进制整数转换成十六进制表示的字符串，tips:已知该行为与Integer类中的toHexString方法一致
     * 测试decToHex (),使用方法引用完成需求
     * ————————————————
     * 版权声明：本文为CSDN博主「行程的天空」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/guanghuotainkong/article/details/115275330
     */
    @Test
    public void test03(){
        decToHex(126, Integer::toHexString);
    }

    public void decToHex(int num ,NumberToString nts){
        String convert = nts.convert(num);
        System.out.println(convert);
    }

}
