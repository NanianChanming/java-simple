package com.nanian.simple.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Caoheng
 * @Date 2022/12/16 15:55
 * @Description
 */
public class CountdownLatchExample {

    public static void main(String[] args) {
        try {
            countdownLatch();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void countdownLatch() throws InterruptedException {
        final Integer threadCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                System.out.println("run..");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end..");
    }

}
