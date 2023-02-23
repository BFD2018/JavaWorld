package com.xjt.javase.juc.future;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureNetMallDemo {

    @Test
    public void test(){
        System.out.println(new Double(3.14) + "abc".charAt(0));     // a ASCII表中位置是97
    }


    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("pdd"),
            new NetMall("taobao"),
            new NetMall("dangdangwang"),
            new NetMall("tmall"));

    //同步请求

    /**
     *  List<NetMall>  ---->   List<String>
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByStep(List<NetMall> list, String productName) {
        List<String> collect = list.stream().map(netMall ->
                String.format(productName + " in %s price is %.2f", netMall.getMallName(), netMall.calcPrice(productName))
        ).collect(Collectors.toList());

        return collect;
    }

    /** 异步 （万箭齐发）
     *  List<NetMall> --->  List<CompletableFuture<String>>  --->  List<String>
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        List<String> stringList = list.stream()
                .map(netMall -> CompletableFuture.supplyAsync(
                        () -> String.format(productName + " in %s price is %.2f", netMall.getMallName(), netMall.calcPrice(productName)))
                ).collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        return stringList;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> stringList = getPriceByStep(list, "mysql");
        System.out.println(stringList);
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - start) +" 毫秒");

        System.out.println("----------------------");

        long start2 = System.currentTimeMillis();
        List<String> stringList2 = getPriceByCompletableFuture(list, "mysql");
        System.out.println(stringList2);
        long endTime2 = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime2 - start2) +" 毫秒");
    }
}

@Data
@AllArgsConstructor
class NetMall {
    private String mallName;

    public double calcPrice(String productName) {
        //检索需要1秒钟
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}



