/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filosofos;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class Filosofo {

    public static class Tenedor{
        
    }
    
    public static class Filosofar implements Runnable{

        public static Random r = new Random();
        private final Tenedor izquierdo, derecho; 
        
        private String nombre; 
        
        public Filosofar(String nombre, Tenedor iz, Tenedor der){
            this.nombre = nombre; 
            this.izquierdo = iz; 
            this.derecho = der; 
        }
        
        @Override
        public void run() {
            while(true){
                pensar(); 
                comer(); 
            }
        }
        
        private void pensar(){
            System.out.println("<" + this.nombre + "> piensa" );
            try { 
                Thread.sleep(r.nextInt(2000) + 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        private void comer(){
            System.out.println(this.nombre + "Tiene hambre y agarra el tenedor izquierdo!");
            try { 
                Thread.sleep(r.nextInt(400) + 600);
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            synchronized(this.izquierdo){
                System.out.println(this.nombre + " Agarra el tenedor derecho ");
                try { 
                    Thread.sleep(r.nextInt(400) + 600);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                synchronized(this.derecho){
                    System.out.println("<"+this.nombre+"> comiendo");
                    try { 
                        Thread.sleep(r.nextInt(2000) + 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                System.out.println("Ha soltado el tenedor derecho!");
                
            }
            
            System.out.println("Ha soltado el tenedor izquierdo!");
        }
        
    }
    
    
    public static void main(String[] args) {
        
        String filosofos[] = {"Aristoteles", "Kant", "Freud", "Platón"}; 
        
        Tenedor tenedores[] = new Tenedor[filosofos.length]; 
        for(int i=0; i < tenedores.length ; i++){
            tenedores[i] = new Tenedor(); 
        }
        
        Filosofar filosofar[] = new Filosofar[filosofos.length]; 
        for(int i=0; i < filosofar.length ; i++){
            Tenedor izquierdo = tenedores[i % tenedores.length]; 
            Tenedor derecho = tenedores[(i + 1) % tenedores.length]; 
            filosofar[i] = new Filosofar(filosofos[i], izquierdo, derecho); 
        }
        
        for(Filosofar f: filosofar){
            new Thread(f).start(); 
        }
    }

    
}
