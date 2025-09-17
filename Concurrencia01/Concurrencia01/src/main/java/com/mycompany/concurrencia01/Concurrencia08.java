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
public class Concurrencia08 {
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
            System.out.println("Hilo actual : " + Thread.currentThread().threadId());
            System.out.println(id + ": iniciamos");
            try{
                Thread.sleep(Duration.of(amount, unit.toChronoUnit())); 
                System.out.println(id + ": terminamos");
            }catch(InterruptedException e){
                System.out.println(e.getStackTrace());
            }
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hilo actual : " + Thread.currentThread().getName());
        var service = Executors.newSingleThreadExecutor(); // El executor service es su propio hilo y nunca va a parar 
        
        // Solo se va a ejecutar un hilo tras otro
        service.submit(new TareaFalsa("Tareita", 2, TimeUnit.SECONDS)); 
        service.submit(new TareaFalsa("Tareita2", 2, TimeUnit.SECONDS)); 
        service.submit(new TareaFalsa("Tareita3", 2, TimeUnit.SECONDS)); 
        service.submit(new TareaFalsa("Tareita4", 2, TimeUnit.SECONDS)); 
        service.submit(new TareaFalsa("Tareita5", 2, TimeUnit.SECONDS)); 
        
        System.out.println("Todo ha sido encolado");
        
        service.shutdown(); // Solo con esto el hilo va a dejar de ejecutarse 
        service.awaitTermination(1, TimeUnit.DAYS); // Va a esperar que todos los hilos terminen 
        
    }
}
