package com.rorschach.noticeandwait;

/**
 * 守护线程
 * @author Rorschach
 * @date 2021-3-15 22:02
 */
public class DaemonTest {

    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {

                }
            }
        });

//        daemonThread.setDaemon(true);

        daemonThread.start();

        System.out.println("main thread over");
    }
}
