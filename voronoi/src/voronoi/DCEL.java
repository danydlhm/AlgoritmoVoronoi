/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi;

import java.util.ArrayList;

/**
 *
 * @author JosePertierra
 */
/*
Clase DCEL (Double connected edge list) encargada de almacenar el grafo resultante del algoritmo de Fortune, i.e. el
Voronoi.
*/
public class DCEL {
    
    ArrayList<Vertice> listaVertices;
    ArrayList<Arista> listaAristas; 
//    ArrayList<Cara> listaCaras;
    
    public DCEL(){
        
    }
    
    public DCEL(ArrayList<Vertice> lv,ArrayList<Arista> la){
        
        listaVertices = lv;
        listaAristas = la;
//        listaCaras = lc;
        
    }
    
    public void addVertice(Vertice v){
        listaVertices.add(v);
    }
    
    public void addArista(Arista a){
        listaAristas.add(a);
    }
    
//    public void addCara(Cara c){
//        listaCaras.add(c);
//    }
    
    public boolean puntoEsVertice(Punto p){
        return listaVertices.contains(p);
    }
    
}
