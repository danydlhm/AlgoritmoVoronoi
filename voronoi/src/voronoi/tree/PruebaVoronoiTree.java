/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tree;

import voronoi.Punto;

/**
 *
 * @author JosePertierra
 */
public class PruebaVoronoiTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        VoronoiTree arbolito = new VoronoiTree();
        Punto nuevoPunto = new Punto(3,4);
        arbolito.insert(nuevoPunto);
        System.out.println(arbolito.toString());
        nuevoPunto = new Punto(2,1);
        arbolito.insert(nuevoPunto);
        System.out.println(arbolito.toString());
        nuevoPunto = new Punto(8,0);
        arbolito.insert(nuevoPunto);
        System.out.println(arbolito.toString());
        nuevoPunto = new Punto(7,-1);
        arbolito.insert(nuevoPunto);
        System.out.println(arbolito.toString());
    }
    
}
