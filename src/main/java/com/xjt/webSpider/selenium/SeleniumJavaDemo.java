package com.xjt.webSpider.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumJavaDemo {
    public static void main(String[] args) {
        //open baidu search
        openBaiduSearch();
    }

    private static void openBaiduSearch() {
        System.setProperty("webdriver.chrome.driver", "D:\\softwares\\chromedriver.exe");

        // 1.创建webdriver驱动
        WebDriver driver = new ChromeDriver();
        // 2.打开百度首页
        driver.get("https://www.baidu.com");
        // 3.获取输入框，输入selenium
        driver.findElement(By.id("kw")).sendKeys("selenium");
        // 4.获取“百度一下”按钮，进行搜索
        driver.findElement(By.id("su")).click();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String pageSource = driver.getPageSource();
        System.out.println(pageSource);

        // 5.退出浏览器
        driver.quit();
    }

}
