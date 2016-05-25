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
        Punto p1=e1.getEvent();
        Punto p2=e2.getEvent();
        int r = Double.compare(p1.getY(), p2.getY());
        if(r!=0){
            return -r;
        }else{
            return -(Double.compare(p1.getX(), p2.getX()));
        }
    }
    
}
