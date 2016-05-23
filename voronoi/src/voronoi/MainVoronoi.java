/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voronoi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



/**
 *
 * @author Patori
 */
public class MainVoronoi {
    
     public static void main(String[] args) {
        //Si se comenta el punto p4, la salida saca bien los vértices (aunque repetidos)
        // Pero las aristas no termina de hacerlas bien  
        // Con tres funciona bien 
        Set<Punto> listapuntos = new HashSet <Punto>();
        Punto p1 = new Punto(0,1);
        Punto p2 = new Punto(2,0);
        Punto p3 = new Punto(-1,0);
//        Punto p4 = new Punto(2,1);
        Punto p5 = new Punto(-1,3);
        Voronoi v = new Voronoi();
        DCEL salida = new DCEL();
        
        listapuntos.add(p1);
        listapuntos.add(p2);
        listapuntos.add(p3);
//        listapuntos.add(p4);
        listapuntos.add(p5);
        
        salida = v.Voronoi(listapuntos);
        System.out.println("--- Salida ---");
        
        for (Vertice vert : salida.getListaVertices()){
            
            System.out.println("Vertice: " + vert);
        }
        for (Arista aris : salida.getListaAristas()){
            System.out.println("Nacimiento: " + aris.getpNacimiento());
            System.out.println("Fin: " + aris.getpFin());
            System.out.println("Cara1: " + aris.getCara1());
            System.out.println("Cara2: " + aris.getCara2());
        }
    }
    
}
