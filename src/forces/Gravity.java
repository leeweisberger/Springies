package forces;

import jboxGlue.PhysicalObject;

public class Gravity extends GlobalForce {
	String[] myGravity;
	public Gravity(String[] readInGravityConstant){
		myGravity = readInGravityConstant;
	}
	public void doForce(PhysicalObject mass){
		System.out.println(getToggle());
	    if (getToggle()) {
			double GRAVITY = Integer.parseInt(myGravity[1]);
			double ANGLE = Integer.parseInt(myGravity[0]);
			ANGLE = toRadians(ANGLE);
			mass.setForce(GRAVITY * Math.cos(ANGLE), GRAVITY * Math.sin(ANGLE));
		} else {
			mass.setForce(0,0);
		}
	}
	
	private double toRadians(double ANGLE){
		return ANGLE * (Math.PI/180);
	}
	
}
