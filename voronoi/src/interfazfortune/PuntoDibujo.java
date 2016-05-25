/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazfortune;

import java.awt.Graphics2D;

/**
 *
 * @author JosePertierra
 */
public class PuntoDibujo {
    
    private int x;
    private int y;
    
    public PuntoDibujo(int xIn , int yIn){
        
        this.x = xIn;
        this.y = yIn;
        
    }
    
    public void pintarPunto(Graphics2D g){
        
        g.fillOval(this.getX()-5,this.getY()-5, 10, 10);
        
    }
    
    public boolean colision(int a1,int a2){
        
        return (distancia(this.getX(), this.getY(),a1,a2)<5);
        
    }
    
    public double distancia(int a1,int a2,int b1,int b2){        
        return (Math.sqrt(Math.pow((a1-b1), 2) + Math.pow((a2-b2), 2)));
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}
