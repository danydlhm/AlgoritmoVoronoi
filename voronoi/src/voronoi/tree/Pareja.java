/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tree;

import voronoi.Punto;

/**
 *
 * @author Andres
 */
public class Pareja {
    private Punto izquierdo;
    private Punto derecho;
    
    public Pareja(Punto unico){
        this.izquierdo=unico;
    }
    public Pareja(Punto a, Punto b){
        this.izquierdo = a;
        this.derecho = b;
    }
    public Punto getIzquierdo(){
        return izquierdo;
    }
    public Punto getDerecho(){
        return derecho;
    }
    public boolean esPareja(){
        return derecho==null;
    }
}
