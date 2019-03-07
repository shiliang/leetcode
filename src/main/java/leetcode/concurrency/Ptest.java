package leetcode.concurrency;

public class Ptest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread A=new PrintA();
            A.start();
            A.join(1000);
            Thread B=new PrintB();
            B.start();
        }


    }
}
