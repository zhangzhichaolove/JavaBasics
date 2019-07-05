package com.peak.java.basics.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池示例
 * FixedThreadPool 该方法返回一个固定线程数量的线程池。该线程池中的线程数量始终不变。当有一个新的任务提交时，线程池中若有空闲线程，则立即执行。若没有，则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务。
 *
 * SingleThreadExecutor 方法返回一个只有一个线程的线程池。若多余一个任务被提交到该线程池，任务会被保存在一个任务队列中，待线程空闲，按先入先出的顺序执行队列中的任务。
 *
 * CachedThreadPool 该方法返回一个可根据实际情况调整线程数量的线程池。线程池的线程数量不确定，但若有空闲线程可以复用，则会优先使用可复用的线程。若所有线程均在工作，又有新的任务提交，则会创建新的线程处理任务。所有线程在当前任务执行完毕后，将返回线程池进行复用。
 *
 */
public class Main {
    private static ExecutorService pool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            pool.submit(new TestThread(i));
        }
    }
}

class TestThread implements Runnable {
    private int i;

    TestThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.err.println(Thread.currentThread() + "--------" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}