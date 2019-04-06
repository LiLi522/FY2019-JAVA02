package com.ll.testjoin;

public class TestJoin {
    public static void main(String[] args) throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + "开始执行");
        Thread thread2 = new Thread(new MyRunnable(10));
        Thread thread1 = new Thread(new MyRunnable(5,thread2));
        thread1.start();
        thread1.join();
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }
}
