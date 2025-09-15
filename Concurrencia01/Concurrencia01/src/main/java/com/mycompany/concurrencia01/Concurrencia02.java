/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

/**
 *
 * @author pablo
 */
public class Concurrencia02 {
    
    public static class Hilo extends Thread{

        @Override
        public void run() {
            for(;;) {
                boolean inter = Thread.interrupted();  
                if(inter) {
                    System.out.println("He sido interrumpido!");
                    return; 
                }
            } 
        }
        
    }
    
    public static void main(String[] args) {
        
        Hilo uno = new Hilo(); 
        uno.start();
        
        try{
            Thread.sleep(5000); 
            uno.interrupt();
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
