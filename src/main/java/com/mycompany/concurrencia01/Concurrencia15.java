/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author pablo
 */
public class Concurrencia15 {
    
    public static class Proceso implements Callable<Integer>{

        String name; 
        
        static int next ;
        
        public Proceso(String name){
            this.name = name; 
        }
        
        @Override
        public Integer call() throws Exception {
            try{
                System.out.println("Iniciamos " + name);
                Thread.sleep(Duration.ofSeconds(3)); 
                System.out.println("Terminamos " + name);
            }catch(InterruptedException e){
                System.err.println("Se ha cancelado la tarea!"); 
            }
            return next++; 
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService svc = Executors.newFixedThreadPool(1); 
        var future1 = svc.submit(new Proceso("A")); 
        var future2 = svc.submit(new Proceso("B")); 
        Thread.sleep(1000); 
        System.out.println("Cancelamos"); 
        future1.cancel(true); 
        svc.shutdown(); 
    }
    
}
