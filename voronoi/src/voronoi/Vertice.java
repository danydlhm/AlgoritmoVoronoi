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
public class Vertice extends Punto  {
    
    private Arista aristaIncidente;
    
    public Vertice(){
        super();
    }
    
    public Vertice(double x, double y){
        super(x,y);
    }

    /**
     * @return the aristaIncidente
     */
    public Arista getAristaIncidente() {
        return aristaIncidente;
    }

    /**
     * @param aristaIncidente the aristaIncidente to set
     */
    public void setAristaIncidente(Arista aristaIncidente) {
        this.aristaIncidente = aristaIncidente;
    }
    
}
