/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.time.Duration;

/**
 *
 * @author pablo
 */
public class Concurrencia03 {

    public static void main(String[] args) throws InterruptedException {
        
        Thread t = new Thread(() -> {
            try{
                System.out.println("Iniciamos");
                Thread.sleep(Duration.ofSeconds(2)); 
                System.out.println("Terminamos");
            }catch(InterruptedException e){
                System.out.println(e.getStackTrace());
            }
        }); 
        
        t.start();
        while(t.isAlive()){
            System.out.println("Todavía se está ejecutando el hilo");
            t.join(); 
        }
        System.out.println("Listo"); // El "join" se asegura de que no pase el hilo hasta que no termine
        
        /*
        if(t.isAlive()){
            System.out.println("El hilo sigue vivo");
        }else{
            System.out.println("El hilo no está vivo");
        }
        */
    }
    
}
