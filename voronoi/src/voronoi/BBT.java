/*
 * Project made by Silvia Moreno y Daniel de las Heras
 */
package voronoi;

/**
 *
 * @author silvia & daniel
 * BINARY BALANCED TREE
 */
public class BBT {
    private BBT left;
    private BBT right;
    private int height;
    private Punto[] data;
    
    public BBT(){
        this.left=null;
        this.right=null;
        this.height=-1;
        this.data=new Punto[2];
    }

    public BBT(BBT left, BBT right, int height, Punto[] data) {
        this.left = left;
        this.right = right;
        this.height = height;
        this.data = data;
    }

    public void insert(Punto p){
        if(this.data.length==0){
            this.data[0]=p;
            this.height=0;
        }else {
            if(this.left!=null && this.right!=null) { //caso base es el else
                int i = this.deterFrenteParabolas(this.data[0], this.data[1], p);
                if (i == 0){
                    this.getLeft().insert(p);
                    //mas a la izq o derech
                }else{
                    this.getRight().insert(p);
                }
                
            }else {     //Left null and right not null (or viceversa) will never happen
                BBT nuevo = new BBT();
                nuevo.data[0] = p;
                nuevo.data[1] = this.data[0];
                nuevo.left = new BBT();
                nuevo.right = new BBT();
                nuevo.right.insert(this.data[0]);
                nuevo.left.data[0] = this.data[0];
                nuevo.left.data[1] = p;
                nuevo.left.right = new BBT();
                nuevo.left.right.insert(p);
                nuevo.left.left = new BBT();
                nuevo.left.left.insert(this.data[0]);
                this.setArbol(nuevo);
            }
            this.rebalance();
        }
    }
    
    public void rebalance(){
        
    }
    
    
    
    public BBT getLeft() {
        return left;
    }

    public BBT getRight() {
        return right;
    }

    public int getHeight() {
        return height;
    }

    public Punto[] getData() {
        return data;
    }

    public void setLeft(BBT left) {
        this.left = left;
    }

    public void setRight(BBT right) {
        this.right = right;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setData(Punto[] data) {
        this.data = data;
    }
    
    private int deterFrenteParabolas(Punto a, Punto b, Punto site) {    //a izquierdo, b derecho
        double punto1 = (Math.pow(site.getX()-a.getX(),2) + Math.pow(a.getY(),2)- Math.pow(site.getY(),2) )/2*(a.getY()-site.getY());
        double punto2 = (Math.pow(site.getX()-b.getX(),2) + Math.pow(b.getY(),2)- Math.pow(site.getY(),2) )/2*(b .getY()-site.getY());
        if (punto1 < punto2){
            return 0;
        }else{
            return 1;
        }
    }

    private void setArbol(BBT nuevo) {
        this.data = nuevo.data;
        this.right = nuevo.right;
        this.left = nuevo.left;
    }
    
    private void remove(Punto p ){
    //Hay que ver si hay un circle event que ya no sucederá en la lista
    }

    //Prueba
}
