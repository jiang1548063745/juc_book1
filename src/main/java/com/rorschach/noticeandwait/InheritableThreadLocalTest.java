package com.rorschach.noticeandwait;

/**
 * 支持父子访问的ThreadLocal
 * @author Rorschach
 * @date 2021-3-15 22:21
 */
public class InheritableThreadLocalTest {

    private static final ThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        THREAD_LOCAL.set("hello world");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("1 thread: " + THREAD_LOCAL.get());
                    Thread.sleep(1000);
                    System.out.println("2 thread: " + THREAD_LOCAL.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("main: " + THREAD_LOCAL.get());
        Thread.sleep(500);
        THREAD_LOCAL.set("change test ");
        System.out.println("main: " + THREAD_LOCAL.get());
    }
}
