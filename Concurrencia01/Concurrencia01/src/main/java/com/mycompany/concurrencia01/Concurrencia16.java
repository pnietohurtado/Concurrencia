/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author pablo
 */
public class Concurrencia16 {
    static AtomicInteger total = new AtomicInteger();  
    
    public static class Counter implements Runnable{

        String name; 
        
        public Counter(String name){
            this.name = name; 
        }
        
        @Override
        public void run() {
            try{
            System.out.println("Empezamos " + name);
            Thread.sleep(Duration.ofSeconds(3)); 
            for(int i = 0; i < 10000; i++){
                total.incrementAndGet(); 
            }
            System.out.println("Terminamos " + name);
            }catch(InterruptedException e){
                System.out.println("Se ha interrumpido el hilo!");
            }
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Counter("A"));
        Thread t2 = new Thread(new Counter("B")); 
        t1.start(); 
        t2.start(); 
        t1.join();
        t2.join(); 
        System.out.println("Total " + total);
    }
}
