package leetcode.concurrency.PV;

/*
    生产者线程
 */
public class ThreadP extends Thread{
    private Producer producer;
    public ThreadP(Producer producer) {
        this.producer = producer;
    }

    public void run() {
        while (true) {
            producer.setValue();
        }
    }
}
