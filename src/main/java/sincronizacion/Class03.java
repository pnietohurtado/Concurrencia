/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sincronizacion;

/**
 *
 * @author pablo
 */
public class Class03 {

    public static class Contador{
        private int valor = 0; 
        
        public synchronized void ping(){
            int old = this.valor; 
            old = old + 1; 
            this.valor = old; 
        }
        
        public int valor(){
            return valor; 
        }
    }
    
    public static class Contable implements Runnable{
        private Contador cont; 

        public Contable(Contador c){
            this.cont = c; 
        }
        
        @Override
        public void run() {
            cont.ping(); 
        }
        
        
    }
    
    public static int HILOS = 1000; 
        
        public static void iterar() throws InterruptedException{
            Contador c = new Contador(); 
            
            Thread threads[] = new Thread[HILOS]; 
            for (int i = 0; i < HILOS; i++) {
                threads[i] = new Thread(new Contable(c)); 
            }
        }

    
}
