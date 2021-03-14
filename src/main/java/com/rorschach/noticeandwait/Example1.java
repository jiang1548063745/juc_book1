package com.rorschach.noticeandwait;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 案例1
 * @author Rorschach
 * @date 2021-3-14 21:11
 */
public class Example1 {

    private static final int MAX_SIZE = 10;
    private static final ArrayBlockingQueue<Double> QUEEN = new ArrayBlockingQueue<>(MAX_SIZE);

    /**
     * 生产者
     */
    public static void produce() {
        while (true) {
            synchronized (QUEEN) {
                if (QUEEN.size() == MAX_SIZE) {
                    try {
                        // 挂起当前线程 并释放同步块上的锁
                        System.out.println("队列满载 挂起当前线程");
                        QUEEN.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("生产元素...");
                QUEEN.add(Math.random() * 10);
                QUEEN.notifyAll();
            }
        }
    }

    /**
     * 消费者
     */
    public static void consumption() {
        while (true) {
            synchronized (QUEEN) {
                if (QUEEN.size() == 0) {
                    try {
                        // 挂起当前线程 并释放 锁 让生产者可以获取锁 生产元素
                        System.out.println("空队列 挂起当前线程 ");
                        QUEEN.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 消费元素 并通知生产者生产线程
                try {
                    System.out.println("消费: " + QUEEN.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                QUEEN.notifyAll();
            }
        }
    }

    /**
     * wait()函数：
     * 当一个线程调用一个共享变量的wait()方法时，该调用线程会被阻塞挂起，直到发生下面几件事情之一才返回：
     * 1) 其他线程调用了该共享对象的notify()或者notifyAll()方法；
     * 2) 其他线程调用了该线程的interrupt()方法，该线程抛出InterruptedException异常返回
     * 另外需要注意的是，如果调用wait()方法的线程没有事先获取该对象的监视器锁，则调用wait()方法时调用线程会抛出IllegalMonitorStateException异常。
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                produce();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                consumption();
            }
        }).start();
    }
}
