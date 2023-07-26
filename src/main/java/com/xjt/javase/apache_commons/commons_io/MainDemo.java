package com.xjt.javase.apache_commons.commons_io;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2023/7/26 20:23
 */
public class MainDemo {
    public static void main(String[] args) throws Exception {
        String[] strings = {"d:\\home\\temp.json","d:\\home\\123.txt"};

        FileMonitor fileMonitor = new FileMonitor(10);
        fileMonitor.monitor(strings,new MyFileAlterationListener());
        fileMonitor.start();
    }
}
