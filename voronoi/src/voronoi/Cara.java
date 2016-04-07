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
class Cara {
    
    private Arista frontera;
    
    public Cara(){
        
    }
    
    public Cara(Arista a){
        this.setFrontera(a);
    }
    
    
    public Arista boundary(){
        return this.getFrontera();
    }

    /**
     * @return the frontera
     */
    public Arista getFrontera() {
        return frontera;
    }

    /**
     * @param frontera the frontera to set
     */
    public void setFrontera(Arista frontera) {
        this.frontera = frontera;
    }
    
}
