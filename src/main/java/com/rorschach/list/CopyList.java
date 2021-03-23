package com.rorschach.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 多线程下迭代器的弱一致性
 * @author Rorschach
 * @date 2021-3-23 21:28
 */
public class CopyList {

    private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

    public static void main(final String[] args) throws InterruptedException {
        arrayList.add("hello");
        arrayList.add("alibaba");
        arrayList.add("welcome");
        arrayList.add("to");
        arrayList.add("hangzhou");

        Thread threadOne = new Thread(() -> {
            // 设置下标为1的元素为baba
            arrayList.set(1, "baba");
            // 删除元素
            arrayList.remove(2);
            arrayList.remove(3);
        });

       Iterator<String> iterator = arrayList.iterator();

       threadOne.start();
       threadOne.join();

       while (iterator.hasNext()) {
           System.out.println(iterator.next());
       }

        /**
         * 打印正常 证明 Iterator 获取到的是变量副本 这就是迭代器弱一致性的体现
         * CopyOnWriteArrayList使用写时复制的策略来保证list的一致性，
         * 而获取—修改—写入三步操作并不是原子性的，所以在增删改的过程中都使用了独占锁，
         * 来保证在某个时间只有一个线程能对list数组进行修改。
         * 另外CopyOnWriteArrayList提供了弱一致性的迭代器，
         * 从而保证在获取迭代器后，其他线程对list的修改是不可见的，
         * 迭代器遍历的数组是一个快照。另外，CopyOnWriteArraySet的底层就是使用它实现的
         */
    }
}
