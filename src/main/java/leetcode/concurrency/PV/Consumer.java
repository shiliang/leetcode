package leetcode.concurrency.PV;

public class Consumer {
    private String lock;

    public Consumer(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (ValueObject.value.equals("")) {
                    System.out.println("消费者" + Thread.currentThread().getName() + "WAITING了");
                    lock.wait();
                }
                System.out.println("消费者" + Thread.currentThread().getName() + "RUNNABLE了");
                ValueObject.value = "";
                lock.notify();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
