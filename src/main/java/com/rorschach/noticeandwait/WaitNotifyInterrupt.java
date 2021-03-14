package com.rorschach.noticeandwait;

/**
 * 测试Interrupt
 * @author Rorschach
 * @date 2021-3-14 21:59
 */
public class WaitNotifyInterrupt {

    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("--BEGIN--");
                    synchronized (object) {
                        object.wait();
                    }
                    System.out.println("--END--");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        A.start();
        Thread.sleep(1000);
        System.out.println("Begin Interrupt Thread A");
        A.interrupt();
        System.out.println("End Interrupt Thread A");
    }
}
