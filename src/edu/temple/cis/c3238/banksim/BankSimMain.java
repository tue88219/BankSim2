package edu.temple.cis.c3238.banksim;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 */
public class BankSimMain {

    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) throws InterruptedException {
        
        
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        Thread[] threads = new Thread[NACCOUNTS];
        TestThread test = new TestThread(b);

        // Start a thread for each account
        for (int i = 0; i < NACCOUNTS; i++) {
            threads[i] = new TransferThread(b, i, INITIAL_BALANCE);
            threads[i].start();
        }
        b.test.start();
        // Wait for all threads to finish
       /* for (int i = 0; i < NACCOUNTS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                // Ignore this
            }
        }*/
       // test.start();
    }

}




