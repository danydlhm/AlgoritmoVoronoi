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
            while (!this.isLeaf(pos)) {
                Pareja actual = pos.getElement();
                if (actual.getIzquierdo().getX() <= p.getX()) {
                    pos = this.left(pos);
                } else if (actual.getDerecho().getX() >= p.getX()) {
                    pos = this.right(pos);
                } else {
                    //FRENTE DE PARÁBOLAS
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
}
