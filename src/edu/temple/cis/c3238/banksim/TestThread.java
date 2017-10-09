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
        bank.test();
    }
}
    

