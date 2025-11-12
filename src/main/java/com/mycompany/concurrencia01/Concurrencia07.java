/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01;

import java.util.Random;

/**
 *
 * @author pablo
 */
public class Concurrencia07 {
    public static class Tenedor{
        
    }
    
    public static class Filosofo implements Runnable{
        private static Random r = new Random(); 
        private final Tenedor iz, der; 
        
        String nombre; 
        
        public Filosofo(String n, Tenedor iz, Tenedor der){
            this.nombre = n; 
            this.iz = iz; 
            this.der = der; 
        }
        
        @Override
        public void run() {
            while(true){
                pensar(); 
                comer(); 
            }
        }
        
        private void pensar(){
            System.out.println("< " + nombre + " > *pensando" );
            esperar(r.nextInt(2000, 3000)); 
        }
        
        private void comer(){
            System.out.println(nombre + " tiene hambre y agarra el tenedor izquierdo!");
            esperar(r.nextInt(400,600)); 
            
            synchronized(iz){
                System.out.println(nombre + " agarra el tenedor derecho");
                esperar(r.nextInt(400,600)); 
                
                synchronized(der){
                    System.out.println("< " + nombre + " > *comiendo");
                    esperar(r.nextInt(2000, 3000)); 
                }
            }
        }
        
        private void esperar(int ms){
            try{
                Thread.sleep(ms); 
            }catch(InterruptedException r){
                
            }
        }
        
    }
    
    public static void main(String[] args) {
        String[] nombre = {"Aristoteles", "Platón", "Socrates", "Epicuro"}; 
        
        Tenedor[] tenedores = new Tenedor[nombre.length]; 
        for(int i= 0; i < tenedores.length; i++){
            tenedores[i] = new Tenedor(); 
        }
        
        Filosofo filosofos[] = new Filosofo[nombre.length]; 
        for(int i = 0; i < filosofos.length; i++){
            Tenedor iz = tenedores[i % tenedores.length]; 
            Tenedor deer = tenedores[(i+1) % tenedores.length]; 
            filosofos[i] = new Filosofo(nombre[i], iz, deer); 
        }
        
        for(Filosofo f: filosofos){
            new Thread(f).start(); 
        }
    }
}
