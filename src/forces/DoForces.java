package forces;

import org.jbox2d.common.Vec2;

import Objects.Mass;
import springies.GetForces;
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
		if(ToggleForces.getGravityToggle() == true)grav.doForce(myX, myY, myMass);
	}	
	
	private void doViscosity() {
		Viscosity v = new Viscosity();
		Vec2 currentVector = ((Mass)myMass).getVec();
		v.doForce(currentVector.x, currentVector.y, myMass);
	}
	
	private void doWallRepulsion() {
		WallRepulsion wr = new WallRepulsion(myWallArray, myX, myY, GetForces.walls);
		wr.doForce(myX, myY, myMass);
	}
	
	private void doCenterOfMass() {
		CenterOfMass c = new CenterOfMass();
		c.doForce(myX, myY, myMass);
	}
	
	public void doForces(){	
		doGravity();
		doViscosity();
		doWallRepulsion();
		doCenterOfMass();
	}
}
