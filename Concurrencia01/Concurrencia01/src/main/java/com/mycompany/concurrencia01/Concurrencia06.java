/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

/**
 *
 * @author pablo
 */
public class Concurrencia06 {
    public static class Timbre{
        public void timbre(){
            synchronized(this){
                System.out.print("Ding...");
                try{
                    Thread.sleep(2000); 
                    System.out.println("Dong");
                }catch(InterruptedException e){
                    System.out.println("La alarma de incendios empezó a sonar");
                }
            }
            
        }
    }
    
    public static class Visitante implements Runnable{

        Timbre t; 
        
        public Visitante(Timbre t){
            this.t = t; 
        }
        
        @Override
        public void run() {
            t.timbre();
        }
        
    }
    
    public static final int Hilo = 1000; 
    
    public static void main(String[] args) throws InterruptedException {
        Thread threads[] = new Thread[Hilo]; 
        Timbre t = new Timbre(); 
        for(int i = 0; i < Hilo; i++){
            threads[i] = new Thread(new Visitante(t)); 
        }
        
        for(int i = 0; i < Hilo; i++){
            threads[i].start(); 
        }
        
        
    }
}
