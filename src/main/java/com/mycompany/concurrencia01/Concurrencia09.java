/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pablo
 */
public class Concurrencia09 {
    public static class TareaFalsa implements Runnable{

        long amount; 
        TimeUnit unit; 
        String id; 
        
        public TareaFalsa (String id, long duracion, TimeUnit unit){
            this.id = id; 
            this.amount = duracion; 
            this.unit = unit; 
        }
        
        @Override
        public void run() {
            //System.out.println("Hilo actual : " + Thread.currentThread().threadId());
            System.out.println(id + ": iniciamos");
            try{
                Thread.sleep(Duration.of(amount, unit.toChronoUnit())); 
                System.out.println(id + ": terminamos");
            }catch(InterruptedException e){
                System.out.println(e.getStackTrace());
            }
        }
        
    }
    
    public static void main(String[] args) throws Exception{
        
        var service = Executors.newFixedThreadPool(8); 
        for(int i = 0; i < 8; i++){
            service.submit(new TareaFalsa("Tarea " + i, 3, TimeUnit.SECONDS)); 
        }
        service.shutdown(); 
        
    }
}
