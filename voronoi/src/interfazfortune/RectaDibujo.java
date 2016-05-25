/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazfortune;

import java.awt.Graphics2D;

/**
 *
 * @author JosePertierra
 */
public class RectaDibujo {
    
    PuntoDibujo p0;
    PuntoDibujo p1;
    
    public RectaDibujo(PuntoDibujo pNac,PuntoDibujo pFin){
        this.p0 = pNac;
        this.p1 = pFin;
    }
    
    public void pintar(Graphics2D g){
        if (this.p1 != null && this.p0 != null){
            this.p0.pintarPunto(g);
            this.p1.pintarPunto(g);
            g.drawLine(p0.getX(), p0.getY(), p1.getX(), p1.getY());
        }
        else if (this.p1 != null){
            this.p1.pintarPunto(g);
        }else if (this.p0 != null){
            this.p0.pintarPunto(g);
        }
        
    }
    
}
