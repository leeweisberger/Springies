package springies;

import jboxGlue.PhysicalObject;

public class DoForces {
	private double myX;
	private double myY;
	private PhysicalObject myMass;
	PhysicalObject[] myWallArray;
	public DoForces(double xpos, double ypos, PhysicalObject mass,PhysicalObject[] wallarray){
		myX=xpos;
		myY=ypos;
		myMass=mass;
		myWallArray=wallarray;
	}
	private void doGravity() {
		Gravity grav = new Gravity();
		if(Springies.gravToggle==1)grav.doGravity(myMass);
	}	
	private void doViscosity() {
		Viscosity v = new Viscosity();
		v.doViscosity(myX, myY, myMass);
	}
	private void doWallRepulsion() {
		WallRepulsion wr = new WallRepulsion(myWallArray, myX, myY, GetForces.walls);
		wr.doWallRepulsion(myMass);
	}
	private void doCenterOfMass() {
		CenterOfMass c = new CenterOfMass();
		c.doCenterOfMass(myX, myY, myMass);
	}
	public void doForces(){
		doGravity();
		doViscosity();
		doWallRepulsion();
		doCenterOfMass();
	}
}
