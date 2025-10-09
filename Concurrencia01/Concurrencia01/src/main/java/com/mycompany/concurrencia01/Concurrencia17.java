/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.util.concurrent.Executors;

/**
 *
 * @author pablo
 */
public class Concurrencia17 {
    
    public static class Clase{
        public void saludar(){
            System.out.println("Hola desde la clase!");
        }
        
        public void despedirse(){
            System.out.println("Adiós desde la clase!");
        }
    }
    
    public static void main(String[] args) {
        Clase c = new Clase(); 
        var exec = Executors.newFixedThreadPool(3); 
        
        Runnable saludo = () -> c.saludar(); 
        Runnable despedidad = () -> c.despedirse(); 
        
        for(int i = 0; i < 10; i++){
            exec.submit(saludo); 
        }
        
        exec.shutdown();
        
    }
    
}
