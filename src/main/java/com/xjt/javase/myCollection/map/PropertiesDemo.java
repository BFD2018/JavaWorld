package com.xjt.javase.myCollection.map;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();

        InputStream in = new BufferedInputStream(new FileInputStream("D:\\aaa.properties"));
        prop.load(in);     ///加载属性列表
        Iterator<String> it=prop.stringPropertyNames().iterator();
        while(it.hasNext()){
            String key=it.next();
            System.out.println(key+":"+prop.getProperty(key));
        }
        in.close();

        ///保存属性到b.properties文件
        FileOutputStream oFile = new FileOutputStream("D:\\bbb.properties", true);//true表示追加打开
        prop.setProperty("phone", "10086");
        prop.store(oFile, "The New properties file");
        oFile.close();
    }
}
