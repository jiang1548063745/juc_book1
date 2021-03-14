package com.rorschach.create;

/**
 * 创建线程的方式2 实现Runnable接口
 * @author Rorschach
 * @date 2021-3-14 20:51
 */
public class RunnableTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Runnable Thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new Thread(new RunnableTask()).start();
        new Thread(new RunnableTask()).start();
    }
}
