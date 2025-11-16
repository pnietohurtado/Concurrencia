/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fumadores;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablo
 */
public class Fumador {
    
    // Monitor 
    public static class Mesa{
        
       private int p1; 
       private int p2; 
        
        private final int  papel; 
        private final  int  tabaco; 
        private final int cerilla; 
        
        public Mesa(){
            this.p1 = -1; 
            this.p2 = -1; 
            
            this.papel = 0; 
            this.tabaco = 1; 
            this.cerilla = 2; 
        }
        
        
        
        
        // Poner los materiales 
        public synchronized void poner(int m1, int m2){
            try{
                while(p1 != -1 || p2 != -1 ){
                    wait(); 
                }


                this.p1 = m1; 
                this.p2 = m2; 
                
                System.out.println("El estanquero ha colocado " + nombre(p1) + " y " + nombre(p2));
                
                notifyAll(); 

            }catch(InterruptedException e){
                
            }
        }
        
        
        // Fumadores 
        public synchronized void fumar(int numero, int m1 , int m2){
            
            try{
                
                while(!(p1 != -1 &&((p1 == m1 && p2 == m2) ||
                 (p1 == m2 && p2 == m1)))){
                    wait(); 
                }
                
                System.out.println("El fumador con " + nombre(numero) + " coge del mostrador " + nombre(m1) + " y " + nombre(m2));
                this.p1 = -1; 
                this.p2 = -1; 
                
                notifyAll(); 
                Thread.sleep(3000); 
                
            }catch(InterruptedException e){
                
            }
            
        }
        
        private String nombre(int i) {
        return switch(i) {
            case 0 -> "papel";
            case 1 -> "tabaco";
            case 2 -> "cerillas";
            default -> "???";
        };
    }
        
    }
    
    
    public static class Estanquero implements Runnable{
        
        private Random r; 
        private Mesa m; 
        private String material[] = {"Tabaco", "Papel", "Cerilla"}; 
        
        public Estanquero(Mesa m){
            this.r = new Random(); 
            this.m = m; 
        }
        
        @Override
        public  void run() {
            while(true){
                //synchronized(m){

                    

                    int material1, material2; 
                    do{
                        material1 = r.nextInt(3); 
                        material2 = r.nextInt(3); 

                    }while(material2==material1); 

                    m.poner(material1, material2);

                    //notifyAll(); 

                //}
            }
        }
        
    }
    
    public static class Fumon implements Runnable{

        private int hueco1; 
        private int hueco2; 
        private Mesa m ; 
        private int numero; 
        
        public Fumon(int numero,int hueco1, int hueco2, Mesa m){
            this.numero = numero; 
            this.hueco1  = hueco1; 
            this.hueco2 = hueco2; 
            this.m = m; 
        }
        
        @Override
        public void run() {
            while(true){
                
                m.fumar(numero, hueco1, hueco2);
                
                
            }
        }
        
    }
    
    
    public static void main(String[] args) {
        
        Mesa m = new Mesa(); 
        
        Thread estanquero = new Thread(new Estanquero(m)); 
        Thread tabaco = new Thread(new Fumon(1, 0, 2, m)); 
        Thread cerilla = new Thread(new Fumon(2, 1, 0, m)); 
        Thread papel = new Thread(new Fumon(0,1, 2, m)); 
        estanquero.start();
        tabaco.start();
        cerilla.start();
        papel.start();
        
    }
    
}
