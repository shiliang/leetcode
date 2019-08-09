package concurrency.printsequ;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPrint extends Thread {
    private AtomicInteger count;
    private String word = null;
    private int order;
    private int runCount;

    public ThreadPrint(AtomicInteger count,String word,int order,int runCount) {
        this.count=count;
        this.word=word;
        this.order=order;
        this.runCount=runCount;
    }

    public void run() {
        while (true) {
            synchronized (count) {  //同一时刻只能有一个线程得到执行，锁住该对象，执行完释放锁
                if (count.get() % runCount == order) {
                    System.out.println(word + "," + count.get());
                    count.getAndAdd(1);
                    count.notifyAll();  //唤醒所有的线程，notify随机唤醒一个线程

                    try {
                        Thread.sleep(1000);  //停顿一秒减慢速度为了能看清楚点
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        count.wait();  //释放该对象的锁进入等待状态中
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

}
