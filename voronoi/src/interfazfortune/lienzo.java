/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazfortune;

import ArbolVisual.visualizarArbol;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;
import material.tree.Position;
import voronoi.Punto;
import voronoi.tree.FrontIterator;
import voronoi.tree.Pareja;
import voronoi.tree.VoronoiTree;

/**
 *
 * @author JosePertierra
 */
public class lienzo extends JPanel{


    
    ArrayList<PuntoDibujo> listaPuntos = new ArrayList<PuntoDibujo>();
    ArrayList<Parabola> listaPar = new ArrayList<Parabola>();
    VoronoiTree arbol = new VoronoiTree();
    
    public void addPoint(int x,int y){
        
        listaPuntos.add(new PuntoDibujo(x,y));
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.white);
        g2.fillRect(0,0, 2000, 800);
        g2.setColor(Color.blue);
        for (PuntoDibujo punto : this.listaPuntos){
            punto.pintarPunto(g2);
        }
//        if (this.listaPar.size()>0){
//            this.pintarParabolas(g2);
//        }
        if (arbol.size()>0){
            System.out.println("Dibujando frende de parabolas");
            this.dibujarFrente(arbol, g2);
        }
        
    }
    
    public void procesarArbol(){
        this.arbol = new VoronoiTree();
        for (PuntoDibujo punto : this.listaPuntos){
            arbol.insert(new Punto(punto.getX(),punto.getY()));
        }
    }

    public void pintarParabolas(Graphics2D g){
        int ancho = (int) this.getSize().getWidth();
        int x,x2;
        int y,y2;
        for ( x = 0; x <= ancho; x++) {
            for (Parabola par : listaPar){
                if (par.getDirectriz()>=par.getFoco()[1]){
                    y = (int) par.obtenerPunto(x);
                    x2 = x + 5;
                    y2 = (int) par.obtenerPunto(x2);
                    g.draw(new Line2D.Double(x, y, x2, y2)); 
                    par.pintarElementos(g, ancho);
                }
            }
        }
    }
    public void pintarParabola(Graphics2D g,int limIzq,int limDer,Parabola par1){
        int ancho = (int) this.getSize().getWidth();
        int x,x2;
        int y,y2;
        for ( x = limIzq; x <= limDer; x++) {
            for (Parabola par : this.listaPar){
                if (par.getDirectriz()<=par.getFoco()[1]){
                    //System.out.println("Calculando parabola: "+par.toString()+" en: "+x);
                    y = this.getSize().height - (int) par.obtenerPunto(x);
                    x2 = x + 5;
                    y2 = this.getSize().height - (int) par.obtenerPunto(x2);
                    g.draw(new Line2D.Double(x, y, x2, y2)); 
                    //par.pintarElementos(g, ancho);
                }
            }
        }
    }
    
    public void dibujarFrente(VoronoiTree arbol,Graphics2D g){
        FrontIterator<Pareja> iter = new FrontIterator(arbol);
        int anterior = 0;
        Punto aux = null;
        while (iter.hasNext()){
            
            Punto pos = iter.next().getElement().getIzquierdo();
//            System.out.println("Elemento del frente de par: "+pos.toString());
            if (aux == null){
                aux = pos;
            }else{
                Parabola par1 = new Parabola((int) aux.getX(),(int) aux.getY(),(int) this.getSize().getHeight());
                Parabola par2 = new Parabola((int) pos.getX(),(int) pos.getY(),(int) this.getSize().getHeight());
                LinkedList<double[]> inters = par1.getInterseccion(par2);
                
                int limiteSup = this.getSize().height;
                for (double[] k : inters){
//                    System.out.println("Insters de "+par1.toString()+" - "+par2.toString()+": " +k[0]+" , "+k[1]);
                    g.fillOval((int)k[0],(int) k[1], 5, 5);
                    if (k[0] > 0 && k[0]<limiteSup){
                        limiteSup = (int) k[0];
                    }
                }
//                System.out.println("Limites: "+anterior+" - "+limiteSup);
                this.pintarParabola(g, anterior, limiteSup,par1);
                anterior = limiteSup;
            }
        }
        Parabola par1 = new Parabola((int) aux.getX(),(int) aux.getY(),(int) this.getSize().getHeight());
        this.pintarParabola(g, anterior, (int) this.getSize().getWidth(),par1);
    }
    
    public PuntoDibujo buscarColision(int x, int y) {
        for (PuntoDibujo punto : this.listaPuntos){
            
            if (punto.colision(x, y)){
                return punto;
            }
            
        }
        return null;
        
    }
    
    public ArrayList<PuntoDibujo> getPuntos(){
        return this.listaPuntos;
    }
    
    public void setParabolas(ArrayList<Parabola> lista){
        this.listaPar = lista;
    }
    
    public void mostrarArbol() {
        if( arbol.size()>0){
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    System.out.println("Visualizando árbol");
                    (new visualizarArbol(arbol)).setVisible(true);
                }
            });
            
        }
        
    }
    
    public void eliminarTodo(){
        listaPar = new ArrayList();
        listaPuntos = new ArrayList();
        arbol = new VoronoiTree();
    }
}
