package edu.temple.cis.c3238.banksim;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 */
class TransferThread extends Thread {

    private final Bank bank;
    private final int fromAccount;
    private final int maxAmount;

    public TransferThread(Bank b, int from, int max) {
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            try {
                if(i==19999)System.out.println((i+1) + " transfers for thread " +Thread.currentThread().toString());
                int toAccount = (int) (bank.size() * Math.random());
                int amount = (int) (maxAmount * Math.random());
                bank.transfer(fromAccount, toAccount, amount);
            } catch (InterruptedException ex) {
                Logger.getLogger(TransferThread.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        }
        bank.closeBank();
    }
}
