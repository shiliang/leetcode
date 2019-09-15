package concurrency.printsequ;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/*
    打印出零和奇偶数
 */
public class ZeroEvenOdd {
    private int n;
    private int current;
    private final Lock lock = new ReentrantLock();
    private final Condition evenCondition = lock.newCondition();
    private final Condition oddCondition = lock.newCondition();
    private final Condition zeroCondition = lock.newCondition();
    //0表示0， 1表示奇数，2表示偶数
    private int state = 0;
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (current < n) {
                if (state != 0) {
                    zeroCondition.await();
                }
                printNumber.accept(0);
                //System.out.println("零线程");
                if ((current & 1) == 0) {  //是偶数，切换到偶数的线程
                    state = 2;
                    evenCondition.signal();
                } else {        //奇数，切换到奇数的线程
                    state = 1;
                    oddCondition.signal();
                }
                zeroCondition.await();
            }
            oddCondition.signal();
            evenCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (current < n) {
                if (state != 2) {
                    evenCondition.await();
                } else {
                    printNumber.accept(++current);
                    //System.out.println("奇数线程");
                    state = 0;
                    zeroCondition.signal();
                }
            }

        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            while (current < n) {
                if (state != 1) {
                    oddCondition.await();
                } else {
                    printNumber.accept(++current);
                    //System.out.println("偶数线程");
                    state = 0;
                    zeroCondition.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
        IntConsumer consumer = a -> System.out.println(a);
        Thread threadA = new Thread() {
            public void run() {
                try {
                    zeroEvenOdd.zero(consumer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread threadB = new Thread() {
            public void run() {
                try {
                    zeroEvenOdd.even(consumer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread threadC = new Thread() {
            public void run() {
                try {
                    zeroEvenOdd.odd(consumer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        threadA.start();
        threadB.start();
        threadC.start();

    }

}
