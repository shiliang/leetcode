package leetcode.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerInJava {

    public static void main(String args[]) {
        System.out.println("How to use wait and notify method in Java");
        System.out.println("Solving Producer Consumper Problem");
        Queue<Integer> buffer = new LinkedList<>();  //共享队列内存
        int maxSize = 10;
        Thread producer = new Producer(buffer, maxSize, "PRODUCER");
        Thread consumer1 = new Consumer(buffer, maxSize, "CONSUMER1");
        Thread consumer2 = new Consumer(buffer, maxSize, "CONSUMER2");

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}

