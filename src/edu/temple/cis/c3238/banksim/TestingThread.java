/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.cis.c3238.banksim;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bme50
 */
public class TestingThread extends Thread{
    
    private final Bank bank;

    public TestingThread(Bank ban){
        bank = ban;
    }


    @Override
    public void run() {
        bank.test();
    }
    
    
    
    
}
