/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolVisual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import voronoi.Punto;
import voronoi.tree.Pareja;
import voronoi.tree.VoronoiTree;
import voronoi.tree.Position;
import voronoi.tree.Tree;

/**
 *
 * @author JosePertierra
 */
public class visualArbol extends JPanel{

    VoronoiTree arbolito;
    int ancho = 2000;
    final int radio = 20;
    ArrayList<Punto> listaAux = new ArrayList<Punto>();
    visualizarArbol ventana;
    
    
    public void setArbol(VoronoiTree arbol){
        
        this.arbolito = arbol;
    }
    
    public ArrayList<Punto> obtenerPuntos(){
        return listaAux;
    }
    
    public int adjudicarNum(Punto p){
        if (p!=null){
            if (!listaAux.contains(p)){
                listaAux.add(p);
            }
            //System.out.println("Adjudicado: "+p.toString());
            return listaAux.indexOf(p);
        }
        return -1;
    }
    
    public int posicionX(int num,int nivel){
        
        int x = (int) ((num - Math.pow(2,nivel-1)) * radio * 2);
        x += (ancho/2 - Math.pow(2, nivel-1)*radio*2);
        x += Math.pow(2, nivel-1)*radio;
        return x;
        
    }
    
    public String identificador(Pareja p){
        
        if (p.esPareja()){
            return ("P"+listaAux.indexOf(p.getIzquierdo())+
                    "-P"+listaAux.indexOf(p.getDerecho()));
        }else{
            return ("P"+listaAux.indexOf(p.getIzquierdo()));
        }
        
    }
    
    public int[] pintar(Graphics2D g,int num,int nivel,Position<Pareja> pos){
        int x = posicionX(num,nivel);
        int y = nivel * 40;
        if (pos!=null){
            adjudicarNum(pos.getElement().getDerecho());
            adjudicarNum(pos.getElement().getIzquierdo());
            //g.setColor(Color.GREEN);
            //g.fillOval(x, y, radio, radio);
            g.setColor(Color.BLACK);
            g.setBackground(Color.white);
            g.drawString(this.identificador(pos.getElement()), x, y+16);

            try{
                Position<Pareja> izq = this.arbolito.left(pos);
                int[] coord = this.pintar(g, num*2, nivel+1, izq);
                g.setColor(Color.BLUE);
                if (izq!=null){
                    g.drawLine(x+radio/2, y+radio, coord[0]+radio/2, coord[1]);
                }
            }catch (Exception e){
//                System.out.println("No tiene hijo izquierdo: "+e.getMessage());
//                int[] coord = this.pintar(g, num*2 , nivel+1, null);
//                g.setColor(Color.BLUE);
//                g.drawLine(x+5, y+10, coord[0]+5, coord[1]);
            }
            try{
                Position<Pareja> der = this.arbolito.right(pos);
                int[] coord = this.pintar(g, num*2 + 1 , nivel+1, der);
                g.setColor(Color.BLUE);
                if (der!=null){
                    g.drawLine(x+radio/2, y+radio, coord[0]+radio/2, coord[1]);
                }
                
            }catch (Exception e){
//                System.out.println("No tiene hijo derecho: ");
//                e.printStackTrace();
//                int[] coord = this.pintar(g, num*2 + 1 , nivel+1, null);
//                g.setColor(Color.BLUE);
//                g.drawLine(x+5, y+10, coord[0]+5, coord[1]);
            }
        }else{
//            g.setColor(Color.RED);
//            g.drawOval(x, y, 20, 20);
        }
        return new int[]{x,y};
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        ancho = (int) this.getSize().width;
        this.pintar(g2, 1, 1, arbolito.root());
        ArrayList<String> lista = new ArrayList<>();
        for (int k = 0; k < listaAux.size(); k++){
            String str;
            try{
                str = "P"+k+" = "+listaAux.get(k).toString();
            }catch(Exception e){
                str = "P"+k+" = null";
            }
            lista.add(str);
        }
        
        this.ventana.escribirPuntos(lista);
    }
    
    public void setVentana(visualizarArbol v){
        this.ventana = v;
    }
    
}
