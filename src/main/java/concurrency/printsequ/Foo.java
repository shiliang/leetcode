package concurrency.printsequ;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//no.1114按序打印
public class Foo {
    private CountDownLatch countDownLatchOne;
    private CountDownLatch countDownLatchTwo;
    public Foo() {
        countDownLatchOne = new CountDownLatch(1);
        countDownLatchTwo = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        countDownLatchOne.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        countDownLatchOne.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        countDownLatchTwo.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        countDownLatchTwo.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
