package concurrency.printsequ;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//no.1114按序打印
public class Foo {
    int NUM = 3; //有3个线程可以调度
    int count = 0;
    final Lock lock = new ReentrantLock();
    final Condition order = lock.newCondition();
    public Foo() {

    }

    public void orderPrint(Runnable print, int id) throws InterruptedException {
        lock.lock();
        while (count % NUM != id) {
            order.await();
        }
        count = (count + 1) % NUM;
        print.run();
        order.signalAll();
        lock.unlock();
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        orderPrint(printFirst, 0);
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        orderPrint(printSecond, 1);
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        orderPrint(printThird, 2);
    }
}
