/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazfortune;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author JosePertierra
 */
public class Parabola {
    
    private double a;
    private double b;
    private double p;
    private double directriz;
    private double[] vertice;
    private double[] foco;
    
    public Parabola(int x, int y,int dir){
        
        this.a = x;
        this.directriz = dir;
        this.p = - directriz +y;
        this.b = y - p/2;
        foco = new double[]{x,y};
        vertice = new double[]{a,b};
        
    }
    
    public double obtenerPunto(double x){
        double y = 0;
        y = (Math.pow((x-this.getA()),2) + (this.getB()*2*this.getP()))/(2*this.getP());
        return y;
    }
    
    public LinkedList<double[]> getInterseccion(Parabola parIn){
        LinkedList<double[]> lista = new LinkedList<>();
        double p1,p2,a1,a2,b1,b2;
        p1 = this.getP();
        p2 = parIn.getP();
        a1 = this.getA();
        a2 = parIn.getA();
        b1 = this.getB();
        b2 = parIn.getB();
        
        double dosa = (2* (2*p2 - 2*p1));
        double menosb = -(4*p1*a2 - 4*p2*a1);
        if (dosa != 0){
            //System.out.println("Las parábolas tienen interseccion/es");
            
            double valor = Math.pow((4*p1*a2 - 4*p2*a1),2) - 4 * (2*p2 - 2*p1) * 
                (Math.pow(a1, 2) * 2 * p2 - Math.pow(a2, 2) * 2 * p1 + 4*p1*p2*b1 - 4*p1*p2*b2);
            double raiz = Math.sqrt(valor);
            if (valor>=0){
                double x1 = (menosb + raiz)/dosa;
                double x2 = (menosb - raiz)/dosa;
                lista.add(new double[]{x1,this.obtenerPunto(x1)});
                lista.add(new double[]{x2,this.obtenerPunto(x2)});
            }
        }else{
            //System.out.println("Tiene como mucho una solución");
            double x = (Math.pow(a1, 2) * 2 * p2 - Math.pow(a2, 2) * 2 * p1 + 4*p1*p2*b1 - 4*p1*p2*b2)/-menosb;
            lista.add(new double[]{x,this.obtenerPunto(x)});
        }
        return lista;
    }

    /**
     * @return the a
     */
    public double getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public double getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(double b) {
        this.b = b;
    }

    /**
     * @return the p
     */
    public double getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(double p) {
        this.p = p;
    }

    /**
     * @return the directriz
     */
    public double getDirectriz() {
        return directriz;
    }

    /**
     * @param directriz the directriz to set
     */
    public void setDirectriz(double directriz) {
        this.directriz = directriz;
    }

    /**
     * @return the vertice
     */
    public double[] getVertice() {
        return vertice;
    }

    /**
     * @param vertice the vertice to set
     */
    public void setVertice(double[] vertice) {
        this.vertice = vertice;
    }

    /**
     * @return the foco
     */
    public double[] getFoco() {
        return foco;
    }

    /**
     * @param foco the foco to set
     */
    public void setFoco(double[] foco) {
        this.foco = foco;
    }
    
    public void pintarElementos(Graphics g,int ancho){
//        g.setColor(Color.red);
//        g.drawOval((int)this.getVertice()[0], (int)this.getVertice()[1], 5, 5);
//        g.drawLine(0,(int) this.getVertice()[1], ancho, (int) this.getVertice()[1]);
        g.setColor(Color.GREEN);
//        g.drawOval((int) this.getFoco()[0], (int) this.getFoco()[1], 5, 5);
        g.drawLine(0, (int) this.getDirectriz(), ancho, (int) this.getDirectriz());
    }
    
    public String toString(){
        String texto = "Parabola con vertice: "
                + Arrays.toString(this.getVertice());
        return texto;
    }
    
    public boolean equals(Parabola par){
        return (this.getA() == (par.getA()) 
                && this.getB()== (par.getB())
                && this.getDirectriz()== (par.getDirectriz()));
    }
    
}
