package voronoi;

/**
*
* @author Sergio y Francisco
*/

public class SiteEvent extends Event{
	private Punto p;
	
	public SiteEvent(Punto p){
		this.p = p;
	}
	
        @Override
	public Punto getEvent() {
		return p;
	}
	
	public void setP(Punto p) {
		this.p = p;
	}
	
}
