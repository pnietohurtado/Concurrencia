/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sincronizacion;

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
    

    public static void main(String[] args) throws InterruptedException {
        Contador c = new Contador(); 
        
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
        
        h1.start(); 
        h2.start(); 
        
        h1.join(); 
        h2.join(); 
        
        System.out.println("El contador vale " + c.getContador());
        
    }
    
}
