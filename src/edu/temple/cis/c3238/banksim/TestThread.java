/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis.c3238.banksim;


/**
 *
 * @author bme50
 */
public class TestThread extends Thread {

    private final Bank bank;

    public TestThread(Bank b) {
        bank = b;
    }

    @Override
    public void run() {
        int sum = 0;
        for (Account account : bank.accounts) {
                System.out.printf("%s %s%n", Thread.currentThread().toString(), account.toString());
                sum += account.getBalance();
            
            
        }

        if (sum != bank.numAccounts * bank.initialBalance) {
            System.out.println(Thread.currentThread().toString() + " Money was gained or lost");
            System.exit(1);
        } else {
            System.out.println(Thread.currentThread().toString() + " The bank is in balance");
        }
    }
}
    

