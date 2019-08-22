package leetcode.concurrency.PV;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Atom {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(100);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 100; i++) {
            new Thread() {
                @Override
                public void run() {
                    atomicInteger.getAndIncrement();

                    countDownLatch.countDown();
                }
            }.start();
        }

        countDownLatch.await();

        System.out.println(atomicInteger.get());
    }

}
