package concurrency.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/*
    生产者和消费者问题
    有多种实现方式
    https://juejin.im/entry/596343686fb9a06bbd6f888c
 */
public class ProducerConsumerInJava {

    public static void main(String args[]) {
     //创建大小为10的 BlockingQueue
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        //开启 producer线程向队列中生产消息
        new Thread(producer).start();
        //开启 consumer线程 中队列中消费消息
        new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");

    }
}

