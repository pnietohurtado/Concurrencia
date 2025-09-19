 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class Concurrencia10 {
    static void esperar(long amount ){
        try{
            Thread.sleep(Duration.of(amount, (TimeUnit.SECONDS).toChronoUnit())); 
        }catch(InterruptedException e){
            e.getStackTrace(); 
        }
    }
    
    static class Bandeja{
        String pass = null; 
        
        public void setPass(String pass){
            this.pass = pass; 
        }
        
        public String getPass(){
            if(this.pass == null)
                throw new IllegalStateException("Vacío"); 
            return this.pass; 
        }
    }
    
    static class Entrega implements Runnable{
        Bandeja b; 
        
        Entrega(Bandeja b){
            this.b = b; 
        }
        
        @Override 
        public void run(){
            this.b.setPass("1234");
            synchronized(b){
                b.notify();
                esperar(3); 
            }
        }
    }
    
    static class Recibe implements Runnable{
        Bandeja b; 
        
        Recibe(Bandeja b){
            this.b = b; 
        }
        
        @Override 
        public void run(){
            synchronized(b){
                try { 
                    b.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Concurrencia10.class.getName()).log(Level.SEVERE, null, ex);
                }
                String pass = this.b.getPass(); 
                System.out.println("Tiene " + pass.length() + " dígitos!");
            }
            
        }
    }
    public static void main(String[] args) {
        Bandeja b = new Bandeja(); 
        Thread e = new Thread(new Entrega(b)); 
        Thread r = new Thread(new Recibe(b)); 
        e.start(); 
        r.start();  
    }
}
