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
            if(this.left!=null && this.right!=null) {
                if(this.data[0].getX()<p.getX()){
                    this.right = new BBT();
                    this.right.insert(p);
                    this.left = new BBT(this.left,this.right,this.height,this.data);
                    this.data[1]=p;
                    this.height++;
                }else {
                    this.right = new BBT(this.left,this.right,this.height,this.data);
                    this.left = new BBT();
                    this.left.insert(p);
                    Punto l = this.data[0];
                    this.data[0] = p;
                    this.data[1] = l;
                }
            }else {     //Left null and right not null (or viceversa) will never happen
                double half = (this.data[1].getX()-this.data[0].getX())/2;
                half = half+this.data[0].getX();
                if(p.getX()<=half){
                    this.left.insert(p);
                    this.data[0]=this.left.data[1];
                }else {
                    this.right.insert(p);
                    this.data[1]=this.right.data[0];
                }
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
    
    
}
