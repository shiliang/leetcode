package leetcode.concurrency.PV;

public class ThreadC extends Thread {
    public Consumer consumer;
    public ThreadC(Consumer consumer) {
        this.consumer = consumer;
    }

    public void run() {
        while (true) {
            consumer.getValue();
        }
    }
}
