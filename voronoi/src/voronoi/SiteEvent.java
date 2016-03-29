package voronoi;

/**
*
* @author Sergio y Francisco
*/

public class SiteEvent {
	private Punto p;
	
	public SiteEvent(Punto p){
		this.p = p;
	}
	
	public Punto getP() {
		return p;
	}
	
	public void setP(Punto p) {
		this.p = p;
	}
	
}
