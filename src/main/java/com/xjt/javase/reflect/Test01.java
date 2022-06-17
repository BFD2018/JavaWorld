package com.xjt.javase.reflect;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        /*1、获取Class的三种方式*/
        Class<Person> personClass = Person.class;
        System.out.println(personClass);

        Person p1 = new Person();
        Class<? extends Person> p1Class = p1.getClass();
        System.out.println(p1Class);

        try {
            Class<?> aClass = Class.forName("com.xjt.javase.reflect.Person");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*2、构造函数*/
        try {
            //2.1 public构造函数
            Constructor<?>[] constructors = p1Class.getConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("=====================>");
                System.out.println(constructor);
            }

            //2.2 所有构造函数
            for (Constructor<?> declaredConstructor : p1Class.getDeclaredConstructors()) {
                System.out.println("------------->");
                System.out.println(declaredConstructor);
            }

            //2.3 有参构造
            Constructor<? extends Person> constructor1 = p1Class.getConstructor(Integer.class);
            Constructor<? extends Person> constructor2 = p1Class.getConstructor(String.class);
            System.out.println(constructor1);
            System.out.println(constructor2);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testField(){
        /*3、字段*/
        try {
            Class<?> pclass = Class.forName("com.xjt.javase.reflect.Person");
            Constructor<? extends Person> constructor = (Constructor<? extends Person>) pclass.getConstructor(String.class,Integer.class);
            constructor.setAccessible(true);        //暴力反射
            try {
                Person person = constructor.newInstance("zhangsan",18);
                person.doRun();

                /*字段*/
                for (Field declaredField : pclass.getDeclaredFields()) {
                    declaredField.setAccessible(true);
                    System.out.println(declaredField.getName());
                }

                Field age = pclass.getDeclaredField("age");
                age.setAccessible(true);        //暴力反射  获得所有字段
                Object o = age.get(person);
                System.out.println(o);


            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMethods() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> pclass = Class.forName("com.xjt.javase.reflect.Person");
        Constructor<?> constructor = pclass.getDeclaredConstructor(String.class,Integer.class);
        constructor.setAccessible(true);        //暴力反射
        Person person = (Person) constructor.newInstance("zhangsan",18);

        Method doRun = pclass.getDeclaredMethod("doRun");

        doRun.invoke(person);

        Method doSay = pclass.getDeclaredMethod("doSay", String[].class);
        String[] hobbies = {"篮球","足球"};
        doSay.invoke(person,new Object[]{hobbies});
    }

    @Test
    public void testMethods2(){
        // 获取动态类Book1
        Book1 book = new Book1();
        Class class1 = book.getClass();
        // 获取Book1类的所有方法
        Method[] declaredMethods = class1.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            Method method = declaredMethods[i];
            System.out.println("方法名称为：" + method.getName());
            System.out.println("方法是否带有可变数量的参数：" + method.isVarArgs());
            System.out.println("方法的参数类型依次为：");
            // 获取所有参数类型
            Class[] methodType = method.getParameterTypes();
            for (int j = 0; j < methodType.length; j++) {
                System.out.println(" " + methodType[j]);
            }
            // 获取返回值类型
            System.out.println("方法的返回值类型为：" + method.getReturnType());
            System.out.println("方法可能抛出的异常类型有：");
            // 获取所有可能抛出的异常
            Class[] methodExceptions = method.getExceptionTypes();
            for (int j = 0; j < methodExceptions.length; j++) {
                System.out.println(" " + methodExceptions[j]);
            }
            boolean isTurn = true;
            while (isTurn) {
                try { // 如果该成员变量的访问权限为private，则抛出异常
                    isTurn = false;
                    if (method.getName().equals("staticMethod")) { // 调用没有参数的方法
                        method.invoke(book);
                    } else if (method.getName().equals("publicMethod")) { // 调用一个参数的方法
                        System.out.println("publicMethod(10)的返回值为：" + method.invoke(book, 10));
                    } else if (method.getName().equals("protectedMethod")) { // 调用两个参数的方法
                        System.out.println("protectedMethod(\"10\",15)的返回值为：" + method.invoke(book, "10", 15));
                    } else if (method.getName().equals("privateMethod")) { // 调用可变数量参数的方法
                        Object[] parameters = new Object[] { new String[] { "J", "A", "V", "A" } };
                        System.out.println("privateMethod()的返回值为：" + method.invoke(book, parameters));
                    }
                } catch (Exception e) {
                    System.out.println("在设置成员变量值时抛出异常，下面执行setAccessible()方法");
                    method.setAccessible(true); // 设置为允许访问private方法
                    isTurn = true;
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("=============================\n");
        }
    }

    @Test
    public void testBase(){
        String[] strArray={"1","2","3"};
        System.out.println(strArray.getClass().getName());

        String name = "xiong";
        System.out.println(name.getClass().getName());
    }

    @Test
    public void testAnnotation() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> pclass = Class.forName("com.xjt.javase.reflect.UserServiceImpl");
        Constructor<?> constructor = pclass.getDeclaredConstructor();
        constructor.setAccessible(true);        //暴力反射

        Method method1 = pclass.getMethod("testxxoo");
        method1.setAccessible(true);
        for (Annotation declaredAnnotation : method1.getDeclaredAnnotations()) {
            System.out.println(declaredAnnotation);     //@com.xjt.javase.reflect.Xxoo("")
        }
    }
}
