package com.xjt.javase.myCollection.homework;

import java.util.ArrayList;

public class HomeWork1 {
    public static void main(String[] args) {
        ArrayList<News> list = new ArrayList<>();
        News news1 = new News("新冠确诊病例超干万，数百万印度教信徒赴恒河\"圣浴\"引民众担忧");
        News news2 = new News("男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生");
        list.add(news1);
        list.add(news2);

        //倒序遍历
        for (int i = list.size()-1; i >= 0 ; i--) {
            String title = list.get(i).getTitle();
            if(title == null){
                System.out.println("");
            }
            if(title.length() > 15){
                title = title.substring(0,15) + "...";
            }
            System.out.println(title);
        }
    }
}

class News{
    private String title;
    private String content;

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }
}
