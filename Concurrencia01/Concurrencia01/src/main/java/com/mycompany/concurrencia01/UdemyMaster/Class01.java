/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01.UdemyMaster;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class Class01 {

    public static AtomicInteger total = new AtomicInteger(); 
    
    public static class BlockingTask implements Runnable{

        String name; 
        
        public BlockingTask(String name){
            this.name = name; 
        }
        
        @Override
        public void run() {
            try { 
                System.out.println("Iniciamos " + name);
                Thread.sleep(10000);
                System.out.println("Terminamos " + name);
                
            } catch (InterruptedException ex) {
                System.out.println("Exiting the blocking thread! ");
            }
        }
        
    }
    
    public static class ForeverTask implements Runnable{

        String name; 
        
        public ForeverTask(String name){
            this.name = name; 
        }
        
        @Override
        public void run() {
            try{
            
            System.out.println("Iniciamos " + name);
            Thread.sleep(2000); 
            for(;;){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Stop!!");
                }
                total.incrementAndGet(); 
            }
            }catch(InterruptedException e){
                System.out.println("Hilo interrumpido!");
            }
        }
        
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(new BlockingTask("A")); 
        t1.start(); 
        
        t1.interrupt();
        
        Thread t2 = new Thread(new ForeverTask("B")); 
        t2.start();
        t2.interrupt();
    }
    
    
    
}
