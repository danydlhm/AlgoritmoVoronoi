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
		return getP();
	}
	
	public void setP(Punto p) {
		this.p = p;
	}

    /**
     * @return the p
     */
    public Punto getP() {
        return p;
    }
	
}
