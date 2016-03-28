/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author carchenilla
 * @param <Event>
 */
public class FortuneQueue<Event> {
    
    private PriorityQueue<Event> queue;
    
    public FortuneQueue(){
        queue = new PriorityQueue<>(new EventComparator());
    }
    
    public Event poll(){
        return queue.poll();
    }
    
    public Event peek(){
        return queue.peek();
    }
    
    public int size(){
        return queue.size();
    }
    
    public void add(Event e){
        queue.add(e);
    }
    
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    
    public boolean contains(Event e){
        return queue.contains(e);
    }
    
    public boolean remove(Event e){
        return queue.remove(e);
    }
 
    private class EventComparator implements Comparator<Event>{

        @Override
        public int compare(Event e1, Event e2) {
            if ((e1==null)||(e2==null)){
                throw new RuntimeException("Objects are null");
            }
            
            Punto p1=null;
            Punto p2=null;
            if (e1 instanceof CircleEvent){
                p1 = ((CircleEvent) e1).puntoCircleEvent();
            }
            else if (e1 instanceof SiteEvent){
                //pues cogeremos el punto de turno, pero aun no se puede hacer
            }
            if (e2 instanceof CircleEvent){
                p2 = ((CircleEvent) e2).puntoCircleEvent();
            }
            else if (e2 instanceof SiteEvent){
                //pues cogeremos el punto de turno, pero aun no se puede hacer
            }           
            return Double.compare(p1.getX(), p2.getX());
            //Suponemos comparacion de puntos de izquierda a derecha
        }
    }    
}
        
    
