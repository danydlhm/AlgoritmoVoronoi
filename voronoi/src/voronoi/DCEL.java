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
Clase DCEL (Doubly-connected edge list) encargada de almacenar el grafo resultante del algoritmo de Fortune, i.e. el
Voronoi.
*/
public class DCEL {
    
    ArrayList<Vertice> listaVertices = new ArrayList<Vertice>();
    ArrayList<Arista> listaAristas = new ArrayList<Arista>(); 
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
    
    public void updateAristas(Punto v, Punto cara){
        ArrayList<Integer> listPosiciones = new ArrayList();
        
        for (int i=0; i<this.listaAristas.size();i++){
            if (((this.listaAristas.get(i)).getCara1().equals(cara))||((this.listaAristas.get(i)).getCara2().equals(cara))){
                listPosiciones.add(i);
            }
        }
        
        for (Integer a:listPosiciones){
            (this.listaAristas.get(a)).setpFin(v);
        }
        
        Vertice vert=new Vertice(v.getX(),v.getY());
        this.listaVertices.add(vert);
        
    }

    public ArrayList<Vertice> getListaVertices() {
        return listaVertices;
    }

    public ArrayList<Arista> getListaAristas() {
        return listaAristas;
    }
    
    public String toString(){
        String texto = "";
        texto+="\nAristas: ";
        for (Arista a : this.listaAristas){
            texto+= a.toString();
        }
        texto+="\nVertices: ";
        for (Vertice v : this.listaVertices){
            texto+= v.toString();
        }
        
        return texto;
    }
    
}
