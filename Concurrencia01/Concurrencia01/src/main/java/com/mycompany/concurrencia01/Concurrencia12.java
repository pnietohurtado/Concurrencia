/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pablo
 */
public class Concurrencia12 {

    static Lock puerta = new ReentrantLock(); 
    static Condition timbre = puerta.newCondition(); 
    static volatile String paquete = null; 
    
    static void sonar(){
        puerta.lock(); 
        
        try{
            System.out.print("Ding...");
            Thread.sleep(2500); 
            paquete = "PS5 Pro"; 
            System.out.println("Dong");
            
        }catch(InterruptedException e){
            e.getStackTrace(); 
        }finally{
            timbre.signal(); 
            puerta.unlock(); 
        }
    }
    
    static void abrirPuerta() {
        puerta.lock();
        timbre.awaitUninterruptibly();
        System.out.println("Abriendo puerta...");
        if(paquete == null){
            System.err.println("Mi paquete no llega, cuanta furia"); 
        }else{
            System.out.println("Es justo mi " + paquete + " que había pedido");
        }
        puerta.unlock(); 
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> abrirPuerta()); 
        Thread t3 = new Thread(() -> abrirPuerta()); 
        Thread t2 = new Thread(() -> sonar()); 
        Thread t4 = new Thread(() -> sonar()); 
        t1.start();
        t3.start(); 
        t2.start();
        t4.start();
        
        
    }
    
}
