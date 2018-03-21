package com.liyafeng.lock;

/**
 * Created by liyafeng on 2018/3/19.
 */

public class Main {


    /**
     * 互斥锁(独立锁)  exclusive lock / Mutex
     * 共享锁 shared lock
     * 自旋锁 spin lock
     * 互斥锁和自旋锁都是保护共享资源的锁，只允许同一时刻只能有一个保持者
     * 只不过互斥锁在已经有线程持有锁的情况下，当前线程进入睡眠状态
     * 而自旋锁会一直去请求锁，直到请求到为止。
     * ========使用场景=========
     * 互斥锁适用于持有锁时间比较长的时候
     * 自旋锁适合比较短的时间
     * 共享锁，如果这几个线程只是读取，那么用互斥锁的效率太低，这时就可以用共享锁
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true)
                    System.out.println("" + i++);
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
