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
public class Arista {
    
    
    Punto pNacimiento;
    Punto pFin;
    Punto cara1;
    Punto cara2;
    
    
    
    public Arista(Punto pNacimiento, Punto pFin){
        this.pNacimiento = pNacimiento;
        this.pFin = pFin;
    }

    public Arista(Punto pNacimiento, Punto pFin, Punto cara1, Punto cara2) {
        this.pNacimiento = pNacimiento;
        this.pFin = pFin;
        this.cara1 = cara1;
        this.cara2 = cara2;
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

    public Punto getCara1() {
        return cara1;
    }

    public void setCara1(Punto cara1) {
        this.cara1 = cara1;
    }

    public Punto getCara2() {
        return cara2;
    }

    public void setCara2(Punto cara2) {
        this.cara2 = cara2;
    }

    @Override
    public boolean equals(Object obj) {
        return (((Arista)obj).getpNacimiento()==this.getpNacimiento())&&(((Arista)obj).getpFin()==this.getpFin())
                &&(((Arista)obj).getCara1()==this.getCara1())&&(((Arista)obj).getCara2()==this.getCara2());
              
    }
    
    
    public String toString(){
        return "\nArista\nNacimiento: "+(this.pNacimiento==null?"":this.pNacimiento.toString())+
                " Fin: "+(this.pFin==null?"":this.pFin.toString())+
                "\nCara1: "+(this.cara1==null?"":this.cara1.toString())+
                " Cara2: "+(this.cara2==null?"":this.cara2.toString());
    }
}
