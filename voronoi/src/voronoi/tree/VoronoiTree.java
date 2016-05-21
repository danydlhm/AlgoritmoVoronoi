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

    public Position<Pareja> insert(Punto p) {
        Position<Pareja> referencia;
        if (this.isEmpty()) {
            Pareja pareja = new Pareja(p);
            referencia = this.addRoot(pareja);
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
            this.insertLeft(pos, new Pareja(nueva.getDerecho(),nueva.getIzquierdo()));
            this.insertRight(pos, new Pareja(nueva.getDerecho()));
            referencia = pos;
        }
        this.rebalance(this.root());
        return referencia;
    }
    
    private int calculateHeight(Position<Pareja> p) {
        if(this.isLeaf(p)){
            return 1;
        }else{
            int alturaIzquierda = 1 + calculateHeight(this.left(p));
            int alturaDerecha = 1 + calculateHeight(this.right(p));
            return Math.max(alturaDerecha, alturaIzquierda);
        }
    }
    
    
    private void rebalance(Position<Pareja> zPos) {
        if (this.isInternal(zPos)) {
            calculateHeight(zPos);
        }
        while (!this.isRoot(zPos)) { // traverse up the tree towards the
            // root
            zPos = this.parent(zPos);
            calculateHeight(zPos);
            if (!isBalanced(zPos)) {
                // perform a trinode restructuring at zPos's tallest grandchild
                Position<Pareja> xPos = tallerChild(tallerChild(zPos));
                zPos = restructure(xPos);
                calculateHeight(this.left(zPos));
                calculateHeight(this.right(zPos));
                calculateHeight(zPos);
            }
        }
    }
    
    private boolean isBalanced(Position<Pareja> p) {
        int leftHeight = (this.hasLeft(p))?calculateHeight(this.left(p)):0;
        int rightHeight = (this.hasRight(p))?calculateHeight(this.right(p)):0;
        final int bf = leftHeight - rightHeight;
        return ((-1 <= bf) && (bf <= 1));
    }
    
    public Position<Pareja> restructure(Position<Pareja> posNode) {
        BTNode<Pareja> lowKey, midKey, highKey, t1, t2, t3, t4;
        Position<Pareja> posParent = this.parent(posNode); // assumes x has a parent
        Position<Pareja> posGrandParent = this.parent(posParent); // assumes y has a parent
//        boolean nodeLeft = (posNode == bst.binTree.left(posParent));
        boolean nodeLeft = (this.hasLeft(posParent) && (posNode == this.left(posParent)));
//        boolean parentLeft = (posParent == bst.binTree.left(posGrandParent));
        boolean parentLeft = (this.hasLeft(posGrandParent) && (posParent == this.left(posGrandParent)));
        BTNode<Pareja> node = (BTNode<Pareja>) posNode, parent = (BTNode<Pareja>) posParent, grandParent = (BTNode<Pareja>) posGrandParent;
        if (nodeLeft && parentLeft) {// Desequilibrio izda-izda
            lowKey = node;
            midKey = parent;
            highKey = grandParent;
            t1 = lowKey.getLeft();
            t2 = lowKey.getRight();
            t3 = midKey.getRight();
            t4 = highKey.getRight();
        } else if (!nodeLeft && parentLeft) {// Desequilibrio izda-dcha
            lowKey = parent;
            midKey = node;
            highKey = grandParent;
            t1 = lowKey.getLeft();
            t2 = midKey.getLeft();
            t3 = midKey.getRight();
            t4 = highKey.getRight();
        } else if (nodeLeft && !parentLeft) {// Desequilibrio dcha-izda
            lowKey = grandParent;
            midKey = node;
            highKey = parent;
            t1 = lowKey.getLeft();
            t2 = midKey.getLeft();
            t3 = midKey.getRight();
            t4 = highKey.getRight();
        } else { // Desequilibrio dcha-dcha
            lowKey = grandParent;
            midKey = parent;
            highKey = node;
            t1 = lowKey.getLeft();
            t2 = midKey.getLeft();
            t3 = highKey.getLeft();
            t4 = highKey.getRight();
        }

        // put b at z's place
        if (this.isRoot(posGrandParent)) {
            this.subTree(midKey);
        } else {
            BTNode<Pareja> zParent = (BTNode<Pareja>) this.parent(posGrandParent);
            if (posGrandParent == this.left(zParent)) {
                midKey.setParent(zParent);
                zParent.setLeft(midKey);
            } else { // z was a right child
                midKey.setParent(zParent);
                zParent.setRight(midKey);
            }
        }
        // place the rest of the nodes and their children
        midKey.setLeft(lowKey);
        lowKey.setParent(midKey);
        midKey.setRight(highKey);
        highKey.setParent(midKey);
        lowKey.setLeft(t1);
        if (t1 != null) {
            t1.setParent(lowKey);
        }
        lowKey.setRight(t2);
        if (t2 != null) {
            t2.setParent(lowKey);
        }
        highKey.setLeft(t3);
        if (t3 != null) {
            t3.setParent(highKey);
        }
        highKey.setRight(t4);
        if (t4 != null) {
            t4.setParent(highKey);
        }
        this.subTree(this.root());
        return midKey; // the new root of this subtree
    }
    
    private Position<Pareja> tallerChild(Position<Pareja> p) {

        int leftHeight = (this.hasLeft(p))?calculateHeight(this.left(p)):0;
        int rightHeight = (this.hasRight(p))?calculateHeight(this.right(p)):0;

        if (leftHeight > rightHeight) {
            return this.left(p);
        } else if (leftHeight < rightHeight) {
            return this.right(p);
        }

        // equal height children - break tie using parent's type
        if (this.isRoot(p)) {
            return this.left(p);
        }

        if (p == this.left(this.parent(p))) {
            return this.left(p);
        } else {
            return this.right(p);
        }
    }
    
    private boolean deterFrenteParabolas(Punto a, Punto b, Punto site) {    //a izquierdo, b derecho
        double punto1 = (Math.pow(site.getX()-a.getX(),2) + Math.pow(a.getY(),2)- Math.pow(site.getY(),2) )/(2*(a.getY()-site.getY()));
        double punto2 = (Math.pow(site.getX()-b.getX(),2) + Math.pow(b.getY(),2)- Math.pow(site.getY(),2) )/(2*(b .getY()-site.getY()));
        return (punto1 < punto2);
    }
}
