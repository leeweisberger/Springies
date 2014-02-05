package springies;

import jboxGlue.PhysicalObject;


public class Gravity {
	public void doGravity(PhysicalObject mass){
	    double GRAVITY = Integer.parseInt(GetForces.grav[1]);
		double ANGLE = Integer.parseInt(GetForces.grav[0]);
		ANGLE = toRadians(ANGLE);			
		mass.setForce(GRAVITY*Math.cos(ANGLE),GRAVITY*Math.sin(ANGLE));
	}
	public double toRadians(double ANGLE){
		return ANGLE * (Math.PI/180);
	}
	
}
