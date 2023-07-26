package com.xjt.javase.apache_commons.commons_lang;

import lombok.Data;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2023/7/23 11:49
 */
@Data
public class Person {
    private String name;
    private int age;
    private String email;
    private String[]  hobby;

    private String dosome(){
        return "dosome---";
    }
}
