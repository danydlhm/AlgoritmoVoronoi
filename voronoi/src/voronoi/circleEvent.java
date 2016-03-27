/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voronoi;
import JAMA.Matrix;
import JAMA.util.Maths;
import java.lang.reflect.Array;
import voronoi.Punto;
import voronoi.Vertice;
import java.lang.Math; 
/**
 *
 * @author Patori
 */
public class circleEvent {
 
    private Punto puntoIzq;
    private Punto puntoCentro;
    private Punto puntoDere;
    private double[] ecuacion;
    private Vertice centro;
    private Punto puntoCircleEvent;
 
//Constructores    
    public circleEvent(Punto puntoIzq,Punto puntoCentro,Punto puntoDere){
        this.puntoIzq = puntoIzq;
        this.puntoDere = puntoDere;
        this.puntoCentro = puntoCentro;
        this.ecuacion = ecuacionCircunferencia();
        this.centro = calcularVertice();
        this.puntoCircleEvent = puntoCircleEvent();
    }

 //Getter y setter
    public Punto getPuntoIzq() {
        return puntoIzq;
    }

    public Punto getPuntoCentro() {
        return puntoCentro;
    }

    public Punto getPuntoDere() {
        return puntoDere;
    }

    public void setPuntoIzq(Punto puntoIzq) {
        this.puntoIzq = puntoIzq;
    }

    public void setPuntoCentro(Punto puntoCentro) {
        this.puntoCentro = puntoCentro;
    }

    public void setPuntoDere(Punto puntoDere) {
        this.puntoDere = puntoDere;
    }
 
  //Funciones
    public double[] ecuacionCircunferencia(){
        double[] ecuacion = new double[5];
        double b_1 = -((double) Math.pow(puntoIzq.getX(),2)) + ((double)Math.pow(puntoIzq.getY(),2));
        double b_2 = -((double) Math.pow(puntoCentro.getX(),2)) + ((double)Math.pow(puntoCentro.getY(),2));
        double b_3 = -((double) Math.pow(puntoDere.getX(),2)) + ((double)Math.pow(puntoDere.getY(),2));
        double[][] array = {{puntoIzq.getX(),puntoIzq.getY(),1},{puntoCentro.getX(),puntoCentro.getY(),1},
                            {puntoDere.getX(),puntoDere.getY(),1}};
        Matrix A = new Matrix(array);
        double[][] arrayb = {{b_1},{b_2},{b_3}};
        Matrix b = new Matrix(arrayb);
        Matrix x = A.solve(b);
        
        ecuacion[0] = 1;
        ecuacion[1] = 1;
        ecuacion[2] = x.getArray()[0][0];
        ecuacion[3] = x.getArray()[1][0];
        ecuacion[4] = x.getArray()[2][0];

        return ecuacion;
    } 
    
    public Vertice calcularVertice(){
        return new Vertice(-this.ecuacion[2]/2,-this.ecuacion[3]/2);
    }
    
    public Punto puntoCircleEvent(){
        double c = ((double)Math.pow(this.centro.getX(), 2))+this.ecuacion[2]*this.centro.getX()+this.ecuacion[4];
        double y1 = (-this.ecuacion[3] + Math.sqrt((this.ecuacion[3]*this.ecuacion[3])-(4*c)))/(2);
        double y2 = (-this.ecuacion[3] - Math.sqrt((this.ecuacion[3]*this.ecuacion[3])-(4*c)))/(2);
        if (y1>y2) {
            return new Punto(this.centro.getX(),y2);
        }else{
            return new Punto(this.centro.getX(),y1);
        }
    }
}
