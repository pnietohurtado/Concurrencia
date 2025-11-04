/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sincronizacion;

/**
 *
 * @author pablo
 */
public class Class01 {
    
    public static class ClaseComun{
       private int hilo = 0; 
       
       public ClaseComun(){
           
       }
       
       public void setHilo(int n){this.hilo = n; }
       public int getHilo(){return this.hilo; }
    }
    
    public static class Hilo implements Runnable{

        private ClaseComun c; 
        private String letra; 
        
        public Hilo(ClaseComun c, String letra){
            this.c = c; 
            this.letra = letra; 
        }
        
        @Override
        public void run() {
                for(int i = 0; i < 5 ; i++){
                    System.out.println(letra);
                }
              
        }
        
    }
    
    
    public static void main(String[] args) throws InterruptedException {
     
        ClaseComun c = new ClaseComun(); 
        Thread h1 = new Thread(new Hilo(c,"G")); 
        Thread h2 = new Thread(new Hilo(c,"E")); 
        Thread h3 = new Thread(new Hilo(c,"E")); 
        Thread h4 = new Thread(new Hilo(c,"K")); 
        
        
        h1.start();
        h1.join(); 
        h2.start();
        h2.join(); 
        h3.start();
        h3.join(); 
        h4.start();
        h4.join(); 
        
    }
    
}
