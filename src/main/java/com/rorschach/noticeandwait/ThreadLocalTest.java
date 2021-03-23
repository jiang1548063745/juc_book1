package com.rorschach.noticeandwait;

/**
 * ThreadLocalTest
 * @author Rorschach
 * @date 2021-3-15 22:11
 */
public class ThreadLocalTest {

    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    static void print(String str) {
        System.out.println(str + " : " + localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("thread One local variable");
                print("threadOne");
                System.out.println("thread one remove after " + localVariable.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("thread Two local variable");
                print("threadTwo");
                System.out.println("thread two remove after " + localVariable.get());
            }
        }).start();
    }
}
