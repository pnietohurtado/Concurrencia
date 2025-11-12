/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.util.Random;
import java.util.concurrent.Executors;

/**
 *
 * @author pablo
 */
public class Concurrencia13 {
    
    static void Log(String m){
        String thread = Thread.currentThread().getName(); 
        System.out.println(thread + ": " + m);
    }
    
    static void esperar(long ms){
        try{
            Thread.sleep(ms); 
        }catch(InterruptedException e){
            e.getStackTrace(); 
        }
    }
    
    public static class Repositorio{
        Random r = new Random(); 
        
        public void leer (){
            Log("Leyendo"); 
            esperar(r.nextLong(100,300)); 
            Log("He terminado de leer"); 
        }
        
        public void escribir(){
            Log("*escribiendo*, que nadie mire"); 
            esperar(r.nextLong(50,100)); 
            Log("he terminado de escribir"); 
        }
    }

    public static void main(String[] args) {
        
        var repo = new Repositorio(); 
        var tp  = Executors.newFixedThreadPool(4); 
        
        Runnable lectura = () -> repo.leer(); 
        Runnable escritura = () -> repo.escribir(); 
        
        for(int i = 0; i < 10 ; i++){
            tp.submit(lectura); 
        }
        tp.shutdown();
        
    }
    
}
