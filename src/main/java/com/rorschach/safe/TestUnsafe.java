package com.rorschach.safe;

import sun.misc.Unsafe;

/**
 * Unsafe类测试
 * @author Rorschach
 * @date 2021-3-17 20:26
 */
public class TestUnsafe {

    /**
     * 获取UnSafe实例
     */
    private static final Unsafe UNSAFE = Unsafe.getUnsafe();

    /**
     * 记录变量state再类中的偏移值
     */
    private static final long STATE_OFFSET;

    private volatile long state = 0;

    static {
        try {
            STATE_OFFSET = UNSAFE.objectFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        TestUnsafe testUnsafe = new TestUnsafe();
        boolean success = UNSAFE.compareAndSwapInt(testUnsafe, STATE_OFFSET, 0, 1);
        System.out.println(success);
    }
}
