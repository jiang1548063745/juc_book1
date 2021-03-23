package com.rorschach.noticeandwait;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Sleep Test
 * @author Rorschach
 * @date 2021-3-15 20:57
 */
public class SleepTest {

    private static final Lock lock = new ReentrantLock();

    public static void test1(String[] args) {
        Thread  threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("threadOne is in sleep");
                    Thread.sleep(10000);
                    System.out.println("threadOne is in awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread  threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("threadTwo is in sleep");
                    Thread.sleep(10000);
                    System.out.println("threadTwo is in awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        threadOne.start();
        threadTwo.start();
    }

    public static void test2() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("child thread is in sleep");
                    Thread.sleep(10000);
                    System.out.println("child thread is in awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        test2();
    }
}
