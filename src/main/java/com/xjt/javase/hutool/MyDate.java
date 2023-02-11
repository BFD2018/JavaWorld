package com.xjt.javase.hutool;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * https://blog.csdn.net/Eric__JY/article/details/100930448
 * Hutool工具集之DateUtil（日期时间工具）详解使用
 */
public class MyDate {
    @Test
    public void testDateFormat(){
        DateTime offset = DateUtil.offset(new Date(), DateField.MONTH, -2);
        String format = DateUtil.format(offset, "M/d");
        System.out.println(format);
    }
}
