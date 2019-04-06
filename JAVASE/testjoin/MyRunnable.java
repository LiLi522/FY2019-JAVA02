package com.ll.testjoin;

public class MyRunnable implements Runnable {
    private int num;
    private Thread thread = null;
    public MyRunnable(int num) {
        this.num = num;
    }
    public MyRunnable(int num,Thread thread) {
        this.thread = thread;
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task" + num);
        try {
            if (thread != null) {
                System.out.println("join的线程开始执行");
                long start = System.currentTimeMillis();
                thread.start();
                thread.join(3000);
                long end = System.currentTimeMillis();
                System.out.println("join线程使用完毕，用时" + (end - start) + "毫秒");
                System.out.println("继续执行当前线程");
            }
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task" + num + "执行完毕");
    }
}
