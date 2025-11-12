/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.concurrencia01;

/**
 *
 * @author pablo
 */
public class Concurrencia01 {
    
    /* 
        Thread -> Contexto de ejecución. La traza de funciones en las que has estado llamando
    */
    
    public static class HiloUno implements Runnable{

        @Override
        public void run() {
            System.out.println("Hola buenas tardes!");
        }
        
    }
    public static void main(String[] args) {
        
        Thread hilo = new Thread(new HiloUno()); 
        hilo.start();
        
        System.out.println("Hola desde el main!");
        
    }
}
