/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.concurrencia01.UdemyMaster;

/**
 *
 * @author pablo
 */
public class Class02 {
    public static void main(String[] args) {
        
        
        
    }
    
    public static boolean isShadeOfGray(int red, int green, int blue){
        return Math.abs(red - green) < 30 && Math.abs(red - blue) < 30 && Math.abs(green - blue) < 30; 
    }
    
    public static int createRGBFromColors(int red, int green, int blue){
        int rgb = 0; 
        
        rgb |= blue; 
        rgb |= green << 8; 
        rgb |= red << 16; 
        
        rgb |= 0xFF000000; 
        
        return rgb; 
    }
    
    public static int getRed(int rgb){
        return (rgb & 0x00FF000000) >> 16; 
    }
    
    public static int getGreen(int rgb){
        return (rgb & 0x000000FF00) >> 8; 
    }
    
    public static int getBlue(int rgb){
        return rgb & 0x00000000FF; 
    }
}
