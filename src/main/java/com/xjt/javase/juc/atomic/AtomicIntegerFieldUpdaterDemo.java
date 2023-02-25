package com.xjt.javase.juc.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Data
@AllArgsConstructor
@NoArgsConstructor
class BankAccount{
    private String bankName;
    private String bankID;

    public volatile Integer money = 0;
    private static AtomicIntegerFieldUpdater atomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    public synchronized void moneyPlus(){
        money++;
    }

    public void moneyAdd(BankAccount bankAccount){
        atomicIntegerFieldUpdater.incrementAndGet(bankAccount);
    }
}

/**
 * 需求：比如说银行账号类，银行名称bankName 账号id bankID 一般是不会变的
 * 只有money字段 经常发生改变
 * 如果 moneyPlus() 方法加锁  锁住整个对象是很不划算的
 * 比如：你去医院牙疼拔个牙，医生不管三七二十一 上来给你来个全麻，肯定不合适，只需要在病牙四周搞个局部麻醉不就ok了
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static Integer SIZE = 100;

    public static void main(String[] args) {
        BankAccount account = new BankAccount("BBC", "4125689321401230", 0);
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

//        for (int i = 0; i < SIZE; i++) {
//            new Thread(()->{
//                try {
//                    System.out.println(Thread.currentThread().getName() + "\t --->come in");
//                    account.moneyPlus();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    countDownLatch.countDown();
//                }
//            },"A"+i).start();
//        }

        for (int i = 0; i < SIZE; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + "\t --->come in");
                    account.moneyAdd(account);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            },"B"+i).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t --->account.money==" + account.money);
    }
}
