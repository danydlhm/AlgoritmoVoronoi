/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi;

import java.util.PriorityQueue;

/**
 *
 * @author carchenilla
 * @param <Event>
 */
public class FortuneQueue{
    
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
    
    public PriorityQueue getQueue(){
        return queue;
    }
    
    public void removeCircleEvent(){
        PriorityQueue queueAux = new PriorityQueue<>(new EventComparator());
        Event aux;
        for (int i=0; i<queue.size();i++){
            aux = queue.poll();
            if (aux instanceof SiteEvent){
                queueAux.add(aux);
            }
        }
        queue=queueAux;
    }
}