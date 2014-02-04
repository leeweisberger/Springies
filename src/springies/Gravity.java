package springies;


public class Gravity {
	public double[] gravity(){
	    double GRAVITY = Integer.parseInt(Forces.grav[1]);
		double ANGLE = Integer.parseInt(Forces.grav[0]);
		ANGLE = toRadians(ANGLE);	
		return new double[] {GRAVITY,ANGLE};
		//setForce(GRAVITY*Math.cos(ANGLE),GRAVITY*Math.sin(ANGLE));
	}
	public double toRadians(double ANGLE){
		return ANGLE * (Math.PI/180);
	}
}
