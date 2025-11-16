/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Barberia;

/**
 *
 * @author pablo
 */
public class Barberia {
    
    public static class Silla{
        
        private int esperando; 
        
        public Silla(){
            this.esperando = 0; 
        }
        
        public synchronized void esperando(){
            try{
                while(esperando == 0 && ){
                    wait();  
                }

                System.out.println("Se despierta al barbero!");
                System.out.println("Se pone a cortar el pelo!");
                esperando -= 1; 
                Thread.sleep(2000);
                //notifyAll(); 
                
            }catch(InterruptedException e){
                
            }
            
        }
        
        public synchronized void 
        
        
        
    }
    
    
    public static class Barbero implements Runnable{

        private Silla s; 
        
        public Barbero(Silla s){
            this.s = s; 
        }
        
        
        @Override
        public void run() {
            
            while(true){
                s.esperando();
            }
            
        }
        
    }
    
    public static void main(String[] args) {
        
    }
    
}
