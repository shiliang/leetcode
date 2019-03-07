package leetcode.concurrency;

public class PrintB extends Thread{
    private char[] B= {'a','b','c','d','e'};

    @Override
    public void run() {
        int i=0;
        while(i<5) {
            System.out.println(Thread.currentThread().
                    getName()+B[i]);
            i++;
        }
    }
}
