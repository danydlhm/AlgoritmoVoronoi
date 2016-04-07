/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi;

import JAMA.Matrix;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import voronoi.Punto;
import voronoi.Vertice;
import voronoi.DCEL;

/**
 *
 * @author Dany
 */
public class Voronoi {

    BBT arbol;
    DCEL estructuraSalida;
    FortuneQueue colaEv;
    Set<Punto> puntos;
    
    /**
     * @param args the command line arguments
     */
    public Voronoi(Set<Punto> conjunto) {
        // TODO code application logic here
        Set<Punto> puntos = conjunto;
        inicializar();
        
        /*
        Procesamos el primer event, que será siempre un SiteEvent
        */
        Event eventoAux = colaEv.poll();
        if ( eventoAux != null ){
            arbol.insert(((SiteEvent) eventoAux).getP());
        }
        
        /*
        Bucle de procesado de los events restantes.
        */
        while ( !colaEv.isEmpty()){
            eventoAux = colaEv.poll();
            if ( eventoAux instanceof SiteEvent ){
                procesarSiteEvent((SiteEvent) eventoAux);
            }else if (eventoAux instanceof CircleEvent){
                procesarCircleEvent((CircleEvent) eventoAux);
            }
        }
        
    }
    
    
    /*
    Inserción de puntos en cola de prioridades como SiteEvents

    */
    public void inicializar(){
        
        SiteEvent aux;
        
        for (Punto p : this.puntos){
            
            aux = new SiteEvent(p);
            this.colaEv.add(aux);
            
        }
        
    }
    
    /*
    Handle de SiteEvents
    */
    public void procesarSiteEvent(SiteEvent ev){
        
        arbol.insert(ev.getP());
        Arista nuevArista = new Arista(ev.getP(),null);
        
        
    }
    
    /*
    Handle de CircleEvents
    */
    public void procesarCircleEvent(CircleEvent ev){
        
        Punto vertice = ev.getCentro();
        Punto cara = ev.getPuntoCentro();
        arbol.remove(cara);
        Arista nuevArista = new Arista(vertice,null);
        estructuraSalida.updateArista(vertice,cara);
        
    }
    
}
