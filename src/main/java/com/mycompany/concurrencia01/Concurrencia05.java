/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class Concurrencia05 {
    public static class Contador{
        private int valor = 0; 
        
        public synchronized void ping(){ // Solo un hilo puede ejecutarlo de forma simultánea 
            int old = this.valor; 
            old += 1; 
            this.valor = old; 
        }
        
        public int valor(){
            return valor; 
        }
    }
    
    public static class Contable implements Runnable{

        private Contador cont; 
        
        public Contable (Contador cont){
            this.cont = cont; 
        }
        
        @Override
        public void run() {
            cont.ping(); 
        }
        
    }
    
    public static int Hilos = 1000; 
    
    public static void iterar() throws InterruptedException{
        Contador c = new Contador(); 
        
        Thread threads[] = new Thread[Hilos]; 
        for(int i = 0; i  < Hilos; i++){
            threads[i] = new Thread(new Contable(c)); 
        }
        
        for(int i = 0; i < Hilos; i++){
            threads[i].start();
        }
        
        for(int i = 0; i < Hilos; i++){ // Salimos de este bucle cuando todos los hilos hayan terminado la ejecución 
            threads[i].join();
        }
        
        int total = c.valor(); 
        System.out.println("Resultado final: " + total);
    }
    
    public static void main(String[] args) {
        
        try { 
            for(;;)
            iterar();
        } catch (InterruptedException ex) {
            Logger.getLogger(Concurrencia04.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
