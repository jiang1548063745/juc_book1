package com.rorschach.noticeandwait;

/**
 * @author Rorschach
 * @date 2021-3-15 21:40
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {

                }
            }
        });

        one.start();

        // 设置中断标志
        one.interrupt();

        // 获取中断标志 true
        System.out.println("isInterrupted: " + one.isInterrupted());

        // 获取中断标志并重置
//        System.out.println("isInterrupted" + one.interrupted());

        // 获取中断标志并重置 false
        System.out.println("isInterrupted: " + Thread.interrupted());

        // 获取中断标志 true
        System.out.println("isInterrupted: " + one.isInterrupted());

        one.join();

        System.out.println("main thread is over");
    }
}
