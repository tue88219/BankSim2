package edu.temple.cis.c3238.banksim;

import java.util.concurrent.Semaphore;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 */
public class Bank {

    public static final int NTEST = 10;
    protected final Account[] accounts;
    public Semaphore sem;
    private long ntransacts = 0;
    protected final int initialBalance;
    protected final int numAccounts;
    private boolean open;
    TestThread test = new TestThread(this);

    public Bank(int numAccounts, int initialBalance) {

        open = true;
        this.initialBalance = initialBalance;
        this.numAccounts = numAccounts;
        sem = new Semaphore(10);
        accounts = new Account[numAccounts];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(this, i, initialBalance);
        }
        ntransacts = 0;
    }

    public void transfer(int from, int to, int amount) throws InterruptedException {

        accounts[from].waitForAvailableFunds(amount);
        sem.acquire();
        if (!open) {sem.release();return;}
            if (accounts[from].withdraw(amount)) {
                accounts[to].deposit(amount);
            }
        sem.release();
        //System.out.println("Available permits: " + sem.availablePermits());
        if (shouldTest()) test();

    }

    public void test() throws InterruptedException{

        try {
            sem.acquire(10);
        
        }finally {
            test.run();
            sem.release();
            sem.release();
            sem.release();
            sem.release();
            sem.release();
            sem.release();
            sem.release();
            sem.release();
            sem.release();
            sem.release();
           // System.out.println("Available: " + sem.availablePermits());
        }
    }

    public int size() {
        return accounts.length;
    }
    
    public synchronized boolean isOpen() {return open;}
    
    public void closeBank() {
        synchronized (this) {
            open = false;
        }
        for (Account account : accounts) {
            synchronized(account) {
                account.notifyAll();
            }
        }
    }
    
    public synchronized boolean shouldTest() {
        return ++ntransacts % NTEST == 0;
    }

}
