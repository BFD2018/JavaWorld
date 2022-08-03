# Java-World教程

## IDEA开发工具



## Java基础

### 基本数据类型

### 数组

### 遍历

### 函数

## 面向对象OOP

### 类的组成

### 类与对象

### 关键字

> 1、48个关键字：abstract、assert、boolean、break、byte、case、catch、char、class、continue、default、do、double、else、enum、extends、final、finally、float、for、if、implements、import、int、interface、instanceof、long、native、new、package、private、protected、public、return、short、static、strictfp、super、switch、synchronized、this、throw、throws、transient、try、void、volatile、while。

> 2、2个保留字（现在没用以后可能用到作为关键字）：goto、const。

> 3、3个特殊直接量：true、false、null。

#### this

#### super

#### static

#### final

#### transient

#### volatile

#### enum

### 构造方法

### 修饰符

#### private

#### protected

#### public

#### default

### 面向对象三大特性

#### 封装

#### 继承 

#### 多态

### 接口

### Object类

### 包

### 抽象类

### 内部类



## Collection集合

### 1.List接口

#### 1.1 ArrayList

#### 1.2 Vector

#### 1.3 LinkedList

#### 

### 2.Map接口

#### 2.1 HashMap

#### 2.2 TreeMap

#### 2.3 HashTable

#### 2.4 LinkedHashMap



### 3.Set接口

#### 3.1 TreeSet

#### 3.2 HashSet

#### 3.3 LinkedHashSet



### 4.Collections工具类



## 网络编程

### 网络基础

### TCP/UDP

### InetAddress 类

### Socket

## Java常用类

### 字符串类

#### String类

#### StringBuffer

#### StringBuilder

### Math类

### Arrays类

### System类

### 数字类

#### Integer 类

#### Float 类

#### Double 类

#### Number 类

### Boolean 类

### Byte 类

### Character 类

### 日期类

#### Date

#### SimpleDateFormat

#### Calendar

#### LocalDate

#### LocalTime

#### LocalDateTime

#### DateTimeFormatter

#### Instant时间戳



## Java异常处理

try

catch

throw

throws

finally

## File类和IO流



## 反射

### Class类

### 类加载机制

### 获取Class类对象

### 反射获取类成员

### 反射获取类构造方法

### 反射获取类字段

### 反射创建对象

## 注解

https://www.runoob.com/w3cnote/java-annotation.html

### 内置注解

Java 定义了一套注解，共有 7 个，3 个在 java.lang 中，剩下 4 个在 java.lang.annotation 中。

**作用在代码的注解是**

- @Override - 检查该方法是否是重写方法。如果发现其父类，或者是引用的接口中并没有该方法时，会报编译错误。
- @Deprecated - 标记过时方法。如果使用该方法，会报编译警告。
- @SuppressWarnings - 指示编译器去忽略注解中声明的警告。

作用在其他注解的注解(或者说 元注解)是：

- @Retention - 标识这个注解怎么保存，是只在代码中，还是编入class文件中，或者是在运行时可以通过反射访问。
- @Documented - 标记这些注解是否包含在用户文档中。
- @Target - 标记这个注解应该是哪种 Java 成员。
- @Inherited - 标记这个注解是继承于哪个注解类(默认 注解并没有继承于任何子类)

从 Java 7 开始，额外添加了 3 个注解:

- @SafeVarargs - Java 7 开始支持，忽略任何使用参数为泛型变量的方法或构造函数调用产生的警告。
- @FunctionalInterface - Java 8 开始支持，标识一个匿名函数或函数式接口。
- @Repeatable - Java 8 开始支持，标识某注解可以在同一个声明上使用多次。

## 泛型



## JVM

### 类加载器classLoader



## JUC并发编程



## 函数式接口



## Java8特性

### Stream流
### 函数式编程(lambda表达式)

## 设计模式

https://www.pdai.tech/md/dev-spec/pattern/1_overview.html

## 数据结构与算法-韩顺平

https://www.pdai.tech/md/algorithm/alg-basic-overview.html

### 位运算

### 数组、稀疏数组

### 队列、链表

### 栈

### 递归

### 排序算法

冒泡排序、选择排序、插入排序、快速排序、归并排序、希尔排序、基数排序(桶排序)、堆排序、排序速度分析

### 查找算法

二分查找、插值查找、斐波那契查找

### 哈希表

散列、哈希表

### 树结构

二叉树、二叉树与数组转换、二叉排序树(BST)

### 图

### 程序员常用的10种算法

https://www.yuque.com/docs/share/6f92db64-713a-4b4f-a272-c25dc1c2d0b6?# 《数据结构与算法_韩顺平》