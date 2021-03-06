/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voronoi.tree;

import ArbolVisual.visualizarArbol;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import voronoi.CircleEvent;
import voronoi.Event;
import voronoi.FortuneQueue;
import voronoi.Punto;
import interfazfortune.Parabola;
import java.util.LinkedList;

/**
 *
 * @author Andres
 */
public class VoronoiTree extends LinkedBinaryTree<Pareja> {
    
    FortuneQueue colaEventos;
    
    public VoronoiTree(FortuneQueue colaEventosIn){
        this.colaEventos = colaEventosIn;
    }
    
    public VoronoiTree(){
        this(new FortuneQueue());
    }
    
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
                    if(!this.hasLeft(pos)){
                        System.out.println("Soy el hijo descolago no tengo izq"+pos.getElement());
                    }
                    pos = this.left(pos);
                } else {
                    if(!this.hasRight(pos)){
                        System.out.println("Soy el hijo descolago no tengo der"+pos.getElement());
                    }
                    pos = this.right(pos);
                }
            }
            Punto a = pos.getElement().getIzquierdo();
            Position<Pareja> padre = this.parent(pos);
            boolean esIzquierdo;
            if (!this.isRoot(pos)){
                esIzquierdo = this.left(padre)==pos;
                Pareja nueva = new Pareja(p,a);
                this.remove(pos);
                if(esIzquierdo){
                    pos = this.insertLeft(padre, nueva);
                }else{
                    pos = this.insertRight(padre, nueva);
                }
                Position<Pareja> izqAux = this.insertLeft(pos, new Pareja(a,p));
                this.insertLeft(izqAux, new Pareja(a));
                this.insertRight(izqAux, new Pareja(p));
                this.insertRight(pos, new Pareja(a));
                referencia = pos;
            }else{
                Punto p1 = pos.getElement().getIzquierdo();
                Pareja nueva = new Pareja(p,p1);
                this.remove(pos);
                pos = this.addRoot(nueva);
                Position<Pareja> posIzq = this.insertLeft(pos, new Pareja(p1,p));
                this.insertLeft(posIzq, new Pareja(p1));
                this.insertRight(posIzq,new Pareja(p));
                this.insertRight(pos, new Pareja(p1));
                referencia = pos;
            }
        }
        this.rebalance(this.root());
        this.detectCircleEvent();
        return referencia;
    }
    
    private int calculateHeight(Position<Pareja> p) {
        if (p==null){
            return 0;
        }else if(this.isLeaf(p)){
            return 1;
        }else{
            int alturaIzquierda = 1 + calculateHeight(this.left(p));
            int alturaDerecha = 1 + calculateHeight(this.right(p));
            return Math.max(alturaDerecha, alturaIzquierda);
        }
    }
    
    private Position<Pareja> getPosition(Punto p){
        Iterator<Position<Pareja>> iter = this.iterator();
        while (iter.hasNext()){
            Position<Pareja> pos = iter.next();
            if (this.isLeaf(pos)){
                Punto puntoAux = pos.getElement().getIzquierdo();
                System.out.println(puntoAux.toString());
                if (puntoAux.equals(p)){
                    return pos;
                }
            }
        }
        return null;
    }

    public Pareja removeHijo(Position<Pareja> p) throws IllegalStateException {
        Position<Pareja> parent = this.parent(p);
        Pareja removed = this.remove(p);
        this.remove(parent);
        
        this.reconstruirParejas(this.root());
        this.rebalance(this.root());
        this.detectCircleEvent();
        
        return removed;
    }
    
    private void rebalance(Position<Pareja> zPos) {
        System.out.println("Rebalanceando");
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
    
    private boolean deterFrenteParabolas(Punto a, Punto b, Punto site) {
        //a izquierdo, b derecho
        Parabola parabola1 = new Parabola((int)a.getX(),(int)a.getY(),(int) site.getY());
        Parabola parabola2 = new Parabola((int)b.getX(),(int)b.getY(),(int) site.getY());
        LinkedList<double[]> interseccion = parabola1.getInterseccion(parabola2);
        double [] pInter;
        if (a.getX() < b.getX()){
        pInter = interseccion.get(0);
        }else if(a.getX() > b.getX()){
            pInter = interseccion.get(1);
        }else{
            pInter = interseccion.get(0);
            System.out.print("Los puntos son iguales!!!!!!");
        }
        return (site.getX() < pInter[0] );
    }
    
    @Override
    public String toString(){
        int nivel = 0;
        int contador = 1;
        String texto = "Árbol: \n";
        Iterator<Position<Pareja>> iter = this.iterator();
        while (iter.hasNext()){
            texto += " - "+iter.next().getElement().toString();
            if ((Math.pow(2,nivel) - contador) == 0){
                texto += "\n";
                contador = 1;
                nivel += 1;
            }else{
                contador += 1;
            }
        }
        return texto;
        
    }
    
    public void detectCircleEvent(){
        
        Iterator<Position> iter = new FrontIterator(this);
        ArrayDeque<Position> cola = new ArrayDeque<Position>();
        String text = "";
        while ( iter.hasNext()){
            Position pAux = iter.next();
            text += " - " +((Pareja) pAux.getElement()).getIzquierdo().toString();
            if (cola.size() == 3){
                Position[] arrAux = Arrays.copyOf(cola.toArray(),cola.size(),Position[].class);
                Punto a1,a2,a0;
                a0 = ((Position<Pareja>) arrAux[0]).getElement().getIzquierdo();
                a1 = ((Position<Pareja>) arrAux[1]).getElement().getIzquierdo();
                a2 = ((Position<Pareja>) arrAux[2]).getElement().getIzquierdo();
                if (!(a0.equals(a1) || a1.equals(a2) || a0.equals(a2))){
                    System.out.println("Son todos distintos:");
                    System.out.println(a0 + " - "+ a1 + " - "+a2);
                    CircleEvent nuevoEvento = new CircleEvent(a0,a1,a2,(Position<Pareja>) arrAux[1]);
                    this.colaEventos.add(nuevoEvento);
                }
                cola.pop();
                cola.add(pAux);
            }else{
                cola.add(pAux);
            }
            
        }
        if (cola.size() == 3){
                Position[] arrAux = Arrays.copyOf(cola.toArray(),cola.size(),Position[].class);
                Punto a1,a2,a0;
                a0 = ((Position<Pareja>) arrAux[0]).getElement().getIzquierdo();
                a1 = ((Position<Pareja>) arrAux[1]).getElement().getIzquierdo();
                a2 = ((Position<Pareja>) arrAux[2]).getElement().getIzquierdo();
                if (!(a0.equals(a1) || a1.equals(a2) || a0.equals(a2))){
                    System.out.println("Son todos distintos:");
                    System.out.println(a0 + " - "+ a1 + " - "+a2);
                    CircleEvent nuevoEvento = new CircleEvent(a0,a1,a2,(Position<Pareja>) arrAux[1]);
                    this.colaEventos.add(nuevoEvento);
                }
            }
        System.out.println("Hojas: \n"+text);
    }
    
    public void addToColaEventos(Event e){
        this.colaEventos.add(e);
    }
    public Event pollColaEventos(){
        return this.colaEventos.poll();
    }
    public FortuneQueue getColaEventos() {
        return colaEventos;
    }
    public void mostrarArbol() {
        VoronoiTree arbol = this;
        if( arbol.size()>0){
           
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    System.out.println("Visualizando árbol");
                    (new visualizarArbol(arbol)).setVisible(true);
                }
            });  
        }  
    }

    
    public void reconstruirParejas(Position<Pareja> p){
        if(!this.isLeaf(p)){
           this.reconstruirParejas(this.left(p));
           this.reconstruirParejas(this.right(p));
           p.getElement().setIzquierdo(getRightLeaf(this.left(p)));
           p.getElement().setDerecho(getLeftLeaf(this.right(p)));        
        }
    }
    
    public Punto getLeftLeaf(Position<Pareja> p){
        Position<Pareja> aux = p;
        while (!this.isLeaf(aux)){
            aux = this.left(aux);
        }
        return aux.getElement().getIzquierdo();
    }
    
    public Punto getRightLeaf(Position<Pareja> p){
        Position<Pareja> aux = p;
        while (!this.isLeaf(aux)){
            aux = this.right(aux);
        }
        return aux.getElement().getIzquierdo();
    }
}
