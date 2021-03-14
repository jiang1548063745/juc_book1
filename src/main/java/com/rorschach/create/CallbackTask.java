package com.rorschach.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式3 实现 CallBack接口
 * @author Rorschach
 * @date 2021-3-14 20:55
 */
public class CallbackTask implements Callable<String> {
    @Override
    public String call() {
        return "Task Finish: " + Thread.currentThread().getName();
    }

    /**
     * 使用继承的好处是方便传参 可以在子类添加成员变量 通过setter/构造进行参数传递
     * 使用Runnable方式 则只能使用主线程里面被声明为 final的变量 不
     * 好的地方是Java不支持多继承，如果继承了Thread类，那么子类不能再继承其他类，而Runnable则没有这个限制
     * 前两种方式都没办法拿到任务的返回结果，但是FutureTask方式可以。
     */
    public static void main(String[] args) {
        // 创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new CallbackTask());

        // 启动异步线程
        new Thread(futureTask).start();

        try {
            // 等待任务执行结束 返回结果
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
