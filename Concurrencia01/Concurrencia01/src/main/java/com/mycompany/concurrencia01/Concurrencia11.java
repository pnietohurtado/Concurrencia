/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author pablo
 */
public class Concurrencia11 {
    
    static final Lock microondas = new ReentrantLock(); 
    
    public static void accederAlRecurso() throws InterruptedException{
        //microondas.lock(); 
        if(!microondas.tryLock(500, TimeUnit.MILLISECONDS)) // Devuelve boolean true = Conseguido el lock, false = No conseguido el lock 
            System.out.println("Candado pillado");
        System.out.println("Calentando la comida en el microondas...");
        try{
            Thread.sleep(2000); 
            System.out.println("La comida está lista");
        }catch(InterruptedException e){
            System.out.println(e.getStackTrace());
        }finally{
            microondas.unlock();
        }
        
    }
    
    public static void main(String[] args) {
        Runnable r = () -> accederAlRecurso(); 
        Thread t1 = new Thread(r); 
        Thread t2 = new Thread(r); 
        t1.start(); 
        t2.start(); 
        
    }
    
}
