/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi;

/**
 *
 * @author JosePertierra
 */
class Arista {
    
    
    Vertice vOrigen;
    Vertice vFinal;
    Cara caraIncidente;
    private Arista twin;
    
    public Arista(Vertice v1, Vertice v2){
        vOrigen = v1;
        vFinal = v2;
        twin = new Arista(vFinal,vOrigen);
        twin.setTwin(this);
    }
    
    public Vertice getOrigen(){
        return vOrigen;
    }
    
    
    public void setCaraIncidente(Cara c){
        this.caraIncidente = c;
    }
    
    public Cara getCaraIncidente(){
        return this.caraIncidente;
    }
    
    public Arista next(){
        return this.caraIncidente.boundary();
    }

    /**
     * @return the twin
     */
    public Arista getTwin() {
        return twin;
    }

    /**
     * @param twin the twin to set
     */
    public void setTwin(Arista twin) {
        this.twin = twin;
    }
    
}
