package com.rorschach.create;

/**
 * 创建线程的方式1 继承Thread类
 * @author Rorschach
 * @date 2021-3-14 20:33
 */
public class ExtendThread extends Thread{

    @Override
    public void run() {
        /**
         * 好处: 获取当前线程直接使用this就可以了 不需要Thread.currentThread()
         * 缺点: Java不支持多继承 如果继承了Thread类 就不能再继承别的类 另外任务和代码没有分离
         * 多个线程执行一样的任务需要多分代码
         */
        System.out.println("继承自Thread的线程, " + this.getName());
    }

    public static void main(String[] args) {
        ExtendThread thread = new ExtendThread();
        thread.start();
    }
}
