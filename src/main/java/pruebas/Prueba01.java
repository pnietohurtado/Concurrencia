/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

/**
 *
 * @author pablo
 */
public class Prueba01 {
    static void saludo(){
        System.out.println("Saludando mediante la función!");
    }
    
    public static class Persona{
        private String nombre; 
        private String edad; 
        
        public Persona(String n, String e){
            this.nombre = n; 
            this.edad = e; 
        }
        
        public void introduccion(){
            System.out.println("Mi nombre es " + this.nombre + " y tengo " + this.edad + " años.");
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException{
        
        Runnable saludar = () -> saludo(); 
        Thread t = new Thread(saludar); 
        t.start(); 
        
        Thread.sleep(2000); 
        
        Persona p = new Persona("Pablo", "32"); 
        Runnable persona = () -> p.introduccion(); 
        Thread t2 = new Thread(persona); 
        t2.start();
        
    }
}
