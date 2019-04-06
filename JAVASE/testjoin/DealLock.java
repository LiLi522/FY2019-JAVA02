package com.ll.testjoin;

public class DealLock {
    Object o1 = new Object();
    Object o2 = new Object();
    public static void main(String[] args) {
        DealLock lock = new DealLock();
        Thread thread1 = new Thread(new MyRunnable1(lock.o1,lock.o2),"thread1");
        Thread thread2 = new Thread(new MyRunnable2(lock.o1,lock.o2),"thread2");
        thread1.start();
        thread2.start();
    }
}

class MyRunnable1 implements Runnable {
    private Object o1;
    private Object o2;

    public MyRunnable1() {}
    public MyRunnable1(Object o1,Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
    @Override
    public void run() {
        synchronized (o1) {
            System.out.println(Thread.currentThread().getName() + "已经拿到锁o1，还需要o2的锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "已经拿到o2的锁");
            }
        }

    }
}

class MyRunnable2 implements Runnable {
    private Object o1;
    private Object o2;
    public MyRunnable2() {}
    public MyRunnable2(Object o1,Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized (o2) {
            System.out.println(Thread.currentThread().getName() + "已经拿到o2的锁，还需要o1的锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o1) {
                System.out.println( Thread.currentThread().getName()+"已经锁住o1");
            }
        }

    }
}