package com.rorschach.noticeandwait;

/**
 * @author Rorschach
 * @date 2021-3-14 21:46
 */
public class Example2 {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    /**
     * 当前线程调用共享变量的wait()方法后只会释放当前共享变量上的锁，
     * 如果当前线程还持有其他共享变量的锁，则这些锁是不会被释放的
     */
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取到A 锁
                    synchronized (resourceA) {
                        System.out.println("ThreadA Get ResourceA Lock");

                        // 获取到B锁
                        synchronized (resourceB) {
                            System.out.println("ThreadA Get ResourceB Lock");

                            System.out.println("ThreadA Release Lock");
                            // 释放A锁 并阻塞
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 睡眠 确保该线程建后执行
                    Thread.sleep(1000);
                    // 获取A锁
                    synchronized (resourceA) {
                        System.out.println("ThreadB Get ResourceA Lock");

                        System.out.println("ThreadB Try Get ResourceB Lock ...");
                        // 获取B锁
                        synchronized (resourceB) {
                            System.out.println("ThreadB Get ResourceB Lock");
                            System.out.println("ThreadB Release Lock");
                            // 释放A锁
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println("Main Thread Over");
    }
}
