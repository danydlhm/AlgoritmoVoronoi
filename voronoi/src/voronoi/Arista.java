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
    
    
    Punto pNacimiento;
    Punto pFin;
    
    public Arista(Punto pNacimiento, Punto pFin){
        this.pNacimiento = pNacimiento;
        this.pFin = pFin;
    }
    
    public Punto getpNacimiento() {
        return pNacimiento;
    }

    public Punto getpFin() {
        return pFin;
    }

    public void setpNacimiento(Punto pNacimiento) {
        this.pNacimiento = pNacimiento;
    }

    public void setpFin(Punto pFin) {
        this.pFin = pFin;
    }
    

    
}
