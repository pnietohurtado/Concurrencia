/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Barberia;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class Barberia {
    
    public static class Silla{
        
        private int esperando;
        private boolean durmiendo; 
        
        public Silla(){
            this.esperando = 0; 
            this.durmiendo = true; 
        }
        
        public synchronized void esperando(){
            try{
                while(esperando == 0 ){
                    this.durmiendo = true; 
                    System.out.println("No hay clientes me duermo!");
                    wait();  
                }
                
                System.out.println("Se pone a cortar el pelo!");
                esperando -= 1; 
                notifyAll();
                 
                
            }catch(InterruptedException e){
                
            }
            
        }
        
        public synchronized void comprobar(){
          
                if(esperando >= 5){
                    System.out.println("Sala llena me las piro!");
                    return; 
                }
                
                esperando++; 
                System.out.println("Se añade un cliente a la cola. ESPERANDO -> " + esperando);
                
                if(this.durmiendo){
                    System.out.println("El cliente despierta al barbero");
                    this.durmiendo = false; 
                    notifyAll();
                }
        }
        
        
        
    }
    
    
    public static class Barbero implements Runnable{

        private Silla s; 
        
        public Barbero(Silla s){
            this.s = s; 
        }
        
        
        @Override
        public void run() {
            
            while(true){
                s.esperando();
                try { 
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
    }
    
    public static class Cliente implements Runnable{

        private Random r; 
        private Silla s; 
        
        
        public Cliente(Silla s){
            this.s = s; 
            this.r = new Random(); 
        }
        
        
        @Override
        public void run() {
            while(true){
                
                try { 
                    s.comprobar();
                    
                    Thread.sleep(r.nextInt(5000) + 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
    }
    
    public static void main(String[] args) {
        
        Silla silla = new Silla(); 
        
        Thread barbero = new Thread(new Barbero(silla)); 
        Thread cliente1 = new Thread(new Cliente(silla)); 
        Thread cliente2 = new Thread(new Cliente(silla)); 
        Thread cliente3 = new Thread(new Cliente(silla)); 
        Thread cliente4 = new Thread(new Cliente(silla)); 
        
        barbero.start();
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start(); 
        
        
    }
    
}
