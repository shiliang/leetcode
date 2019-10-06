package concurrency.printsequ;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/*
    打印出零和奇偶数
 */
public class ZeroEvenOdd {


    public static void main(String[] args) {
        ReentrantLock r1 = new ReentrantLock();
        Condition oddcondition = r1.newCondition();
        Condition evencondition = r1.newCondition();
        AtomicInteger num = new AtomicInteger(0);
        Thread even = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num.get() <= 100) {
                    try {
                        r1.lock();
                        if (num.get() % 2 == 0) {
                            System.out.printf("%s:%d\n","偶数线程",num.get());
                            num.getAndIncrement();
                            oddcondition.signalAll();
                        }
                        evencondition.await();  //临时释放锁，等待唤醒之后还会得到锁

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        r1.unlock();
                    }
                }

               }



        }, "偶数线程");

        Thread odd = new Thread(new Runnable() {
            @Override
            public void run() {
                    while (num.get() <= 100) {
                        try {
                            r1.lock();
                            if (num.get() % 2 == 1) {
                                System.out.printf("%s:%d\n","奇数线程",num.get());
                                num.getAndIncrement();
                                evencondition.signalAll();
                            }
                            oddcondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            r1.unlock();
                        }
                    }

            }
        }, "奇数线程");

        odd.start();
        even.start();

    }

}
