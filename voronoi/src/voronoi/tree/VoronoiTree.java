/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tree;

import voronoi.Punto;

/**
 *
 * @author Andres
 */
public class VoronoiTree extends LinkedBinaryTree<Pareja> {

    public void insert(Punto p) {
        if (this.isEmpty()) {
            Pareja pareja = new Pareja(p);
            this.addRoot(pareja);
        } else {
            Position<Pareja> pos = this.root();
            boolean izquierda;
            while (!this.isLeaf(pos)) {
                Pareja actual = pos.getElement();
                izquierda = this.deterFrenteParabolas(actual.getIzquierdo(), actual.getDerecho(), p);
                if (izquierda) {
                    pos = this.left(pos);
                } else {
                    pos = this.right(pos);
                }
            }
            Punto a = pos.getElement().getIzquierdo();
            Position<Pareja> padre = this.parent(pos);
            boolean esIzquierdo = this.left(padre)==pos;
            Pareja nueva = new Pareja(p,a);
            this.remove(pos);
            if(esIzquierdo){
                pos = this.insertLeft(padre, nueva);
            }else{
                pos = this.insertRight(padre, nueva);
            }
            this.insertLeft(pos, new Pareja(nueva.getIzquierdo()));
            this.insertRight(pos, new Pareja(nueva.getDerecho()));
        }
        this.rebalance();
    }
    
    
    private void rebalance() {
        throw new UnsupportedOperationException("TO DO: rebalance()");
    }
    
    private boolean deterFrenteParabolas(Punto a, Punto b, Punto site) {    //a izquierdo, b derecho
        double punto1 = (Math.pow(site.getX()-a.getX(),2) + Math.pow(a.getY(),2)- Math.pow(site.getY(),2) )/(2*(a.getY()-site.getY()));
        double punto2 = (Math.pow(site.getX()-b.getX(),2) + Math.pow(b.getY(),2)- Math.pow(site.getY(),2) )/(2*(b .getY()-site.getY()));
        return (punto1 < punto2);
    }
}
