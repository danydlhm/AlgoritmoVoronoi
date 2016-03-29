/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi;

import java.util.Comparator;

/**
 *
 * @author carche
 */
public class EventComparator implements Comparator<Event> {

    @Override
    public int compare(Event e1, Event e2) {
        if ((e1==null)||(e2==null)){
            throw new RuntimeException("Objects are null");
        }
        Punto p1=null;
        Punto p2=null;
        if (e1 instanceof CircleEvent){
            p1 = ((CircleEvent) e1).puntoCircleEvent(); //se agradeceria un getter del punto, para no calcularlo de nuevo
        }
        else if (e1 instanceof SiteEvent){
            //pues cogeremos el punto de turno, pero aun no se puede hacer
        }
        if (e2 instanceof CircleEvent){
            p2 = ((CircleEvent) e2).puntoCircleEvent(); //se agradeceria un getter del punto, para no calcularlo de nuevo
        }
        else if (e2 instanceof SiteEvent){
            //pues cogeremos el punto de turno, pero aun no se puede hacer
        }           
        return Double.compare(p1.getX(), p2.getX());
        //Suponemos comparacion de puntos de izquierda a derecha
    }
    
}
