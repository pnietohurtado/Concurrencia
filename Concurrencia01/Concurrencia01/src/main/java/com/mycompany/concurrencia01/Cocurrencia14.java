/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author pablo
 */
public class Cocurrencia14 {
    
    static void esperar(Long ms){
        try{
            Thread.sleep(2000); 
        }catch(InterruptedException e){
            System.exit(1); 
        }
    }
    
    
    public static class Repositorio{
        Random r = new Random(); 
        
        ReadWriteLock rwlock = new ReentrantReadWriteLock(); 
        
        public void leer(){
            try{
                rwlock.readLock().lock();
                System.out.println("*Leyendo*");
                esperar(r.nextLong(100, 300)); 
                System.out.println("he terminado de leer!"); 
            }finally{
                rwlock.readLock().unlock();
            }
        }
        
        public void escribir(){
            try{
                rwlock.writeLock().lock();
                System.out.println("*escribiendo*, que nadie mire");
                esperar(r.nextLong(50,100)); 
                System.out.println("he terminado de escribir");
            }finally{
                rwlock.readLock().unlock();
            }
        }
    }
    
    public static void main(String[] args) {
        var repo = new Repositorio(); 
        var tp = Executors.newFixedThreadPool(4); 
        
        Runnable lectura = () -> repo.leer(); 
        Runnable escritura = () -> repo.escribir(); 
        
        for(int i = 0; i < 10; i++){
            tp.submit(lectura); 
        }
        tp.submit(escritura); 
        for(int i = 0; i < 10; i++){
            tp.submit(lectura); 
        }
        
        tp.shutdown();
    }
    
}
