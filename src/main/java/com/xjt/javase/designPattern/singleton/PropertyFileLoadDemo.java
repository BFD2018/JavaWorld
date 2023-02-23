package com.xjt.javase.designPattern.singleton;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyFileLoadDemo {
    public static void main(String[] args) {
        SingleProperty singleProperty = SingleProperty.getInstance();
        System.out.println(singleProperty.getName());
        System.out.println(singleProperty.getPropertyValue("aliyun.config.oss.ak"));
    }

}

class SingleProperty {

    private static Properties prop;

    private static class SinglePropertyHolder{
        private static final SingleProperty singleProperty = new SingleProperty();
    }

    /**
     * config.properties 内容是 test.name=kite
     */
    private SingleProperty(){
        System.out.println("构造函数执行....");
        prop = new Properties();
        String path = SingleProperty.class.getClassLoader().getResource("config.properties").getPath();
        System.out.println(path);

        InputStream stream = SingleProperty.class.getClassLoader()
                .getResourceAsStream("config.properties");
        try {
            prop.load(new InputStreamReader(stream, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SingleProperty getInstance(){
        return SinglePropertyHolder.singleProperty;
    }


    public String getName(){
        return prop.get("test.name").toString();
    }

    public Object getPropertyValue(String key){
        return prop.get(key);
    }
}
