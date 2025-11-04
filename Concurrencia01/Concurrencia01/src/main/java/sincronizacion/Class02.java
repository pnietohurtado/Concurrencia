/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sincronizacion;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class Class02 {

    public static class Contador{
        private int contador = 0; 
        
        public Contador(){}
        public void Sumar(){this.contador++;}
        public int getContador(){return contador;}
    }
    
    public static class ClaseComun{
        private int i; 
        public ClaseComun(){this.i = 0;}
        public void setI(int i){this.i=i;}
        public int getI(){return this.i;}
    }

    public static class Hilo implements Runnable{

        private ClaseComun c; 
        
        public Hilo(ClaseComun c){this.c = c; }
        
        @Override
        public void run() {
            synchronized(c){
                
                try {
                    for(int i = 0 ; i < 5; i++){
                        System.out.println("Contador " + i);
                        c.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Class02.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        Contador c = new Contador(); 
        ClaseComun c2 = new ClaseComun(); 
        
        Thread h1 = new Thread(new Hilo(c2)); 
        Thread h2 = new Thread(new Hilo(c2)); 
        Thread h3 = new Thread(() ->{
            
        }); 
        
        /*
        Thread h1 = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                c.Sumar(); 
            }
        }); 
        
        Thread h2 = new Thread(() -> {
            for(int i = 0; i < 100; i++){
                c.Sumar(); 
            }
        }); 
        */
        h1.start(); 
        h2.start(); 
        /*
        h1.join(); 
        h2.join(); 
        */
        System.out.println("El contador vale " + c.getContador());
        
    }
    
}
