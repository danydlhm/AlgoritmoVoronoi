/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tree;


import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author Sergio
 * @param <E>
 */
public class FrontIterator<E> implements Iterator<Position<E>> {

    private final Queue<Position<E>> nodeQueue;
    private final Tree<E> tree;
    
    public FrontIterator(Tree<E> t){
        tree = t;
        nodeQueue = new ArrayDeque<>();
        putInside(t.root());
    }
    
    public FrontIterator(Tree<E> t, Position<E> start) {
        nodeQueue = new ArrayDeque<>();
        this.tree = t;
        putInside(start);
    } 
    
    @Override
    public boolean hasNext() {
        return nodeQueue.size()>0;
    }

    @Override
    public Position<E> next() {
        return nodeQueue.remove();
    }
    
    private void putInside (Position<E> p){
        if (this.tree.isRoot(p) && this.tree.isLeaf(p)){
            nodeQueue.add(p);
        }
        if (tree.children(p)!=null){
            for (Position<E> pos : tree.children(p)){
                if (tree.isLeaf(pos)){
                    nodeQueue.add(pos);
                }
                else{
                    putInside(pos);
                }
            }
        }
    }    
}