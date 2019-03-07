package leetcode.concurrency;

public class PrintA extends Thread{
    private int[] A= {1,2,3,4,5};

    @Override
    public void run() {
        int i=0;
        while(i<5) {
            System.out.println(Thread.currentThread().
                    getName()+A[i]);
            i++;
        }
    }
}
