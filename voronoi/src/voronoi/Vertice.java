/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi;
 
/**
 *
 * @author Dany
 */
public class Vertice {
    private int x,y;
    
    public Vertice(){
        this.x = 0;
        this.y = 0;
    }
    
    public Vertice(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
