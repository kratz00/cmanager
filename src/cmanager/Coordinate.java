package cmanager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Coordinate implements Serializable
{
	private static final long serialVersionUID = -2526305963690482539L;
	
	private double lat, lon;
	
	public Coordinate(String lat, String lon) {
		this(new Double(lat), new Double(lon));
	}
	
	public Coordinate(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public Double getLat() {
		return lat;
	}
	
	public Double getLon() {
		return lon;
	}
	
	public boolean equals(Coordinate c)
	{
		return lat == c.getLat() && lon == c.getLon();
	}
	
	public String toString(){
		return new Double(lat).toString() + ", " + new Double(lon).toString();
	}
	
	public double distanceSphere(Coordinate c2)
	{
		// http://www.kompf.de/gps/distcalc.html
		// mit dist: Entfernung in km 
		
		double lat1 = (lat + c2.lat) / 2 * Math.PI / 360;
		double dx = 111.3 * Math.cos(lat1) * (lon - c2.lon);
		double dy = 111.3 * (lat - c2.lat);
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public double distanceSphereRounded(Coordinate c2)
	{
		return round(distanceSphere(c2), 3);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
//	public double distance(Coordinate c2)
//	{
//		// quick version
//		// http://www.kompf.de/gps/distcalc.html
//		
//		final double dx = 71.5 * (lon - c2.lon);
//		final double dy = 111.3 * (lat - c2.lat);
//		double distance = Math.sqrt(dx * dx + dy * dy);
//		return distance;
//	}

}
