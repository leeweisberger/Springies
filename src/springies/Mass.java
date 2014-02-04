package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;

import org.jbox2d.common.Vec2;

import forces.WallRepulsion;
import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

import java.math.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Mass extends PhysicalObjectCircle{
	private String myID;
	private static JGColor myColor = JGColor.red;
	private PhysicalObject[] mywallarray;
	private double myMass;
	private boolean isFixed;
	private int force;


	public Mass(String id, double xpos, double ypos,double xvel,double yvel,double mass,PhysicalObject[] wallarray){
		super(id, 1, myColor, 5,mass);
		x=(int)xpos;
		y=(int)ypos;
		setPos(x, y);
		this.setSpeed(xvel, yvel);
		myID = id;
		mywallarray=wallarray;
		myMass = mass;
	}

	public Mass(String id, double xpos, double ypos){
		super(id, 1, myColor, 5,1);
	}

	protected String getID(){
		return myID;
	}

	protected double getMass(){
		return myMass;
	}
	public void setForceKey(int key){
		force=key;
	}

	@Override
	public void move(){
		setJGamePosition();
		doWallRepulsion();
		doViscosity();	
		doCenterOfMass();		
		if(!isFixed)
			doGravity();
	}

	private void doCenterOfMass() {
		CenterOfMass c = new CenterOfMass();
		setForce(c.centerOfMass(x, y)[0],c.centerOfMass(x, y)[1]);
	}

	private void doViscosity() {
		Viscosity v = new Viscosity();
		Vec2 velocity = myBody.getLinearVelocity();	
		setForce(v.viscosity(velocity.x, velocity.y)[0],v.viscosity(velocity.x, velocity.y)[1]);
	}

	private void doGravity() {
		Gravity grav = new Gravity();
		setForce(grav.gravity()[0]*Math.cos(grav.gravity()[1]),grav.gravity()[0]*Math.sin(grav.gravity()[1]));
	}
	
//	private void toggleForces(){
//		if (getKey(key_up) && y>0) 	{
//			ydir=-1;yfacing=-1;xfacing=0;
//		}
//	}

	private void setJGamePosition() {
		if (myBody.m_world != WorldManager.getWorld()) {
			remove();
			return;
		}
		
		// copy the position and rotation from the JBox world to the JGame world
		Vec2 position = myBody.getPosition();
		x = position.x;
		y = position.y;
		myRotation = -myBody.getAngle();
	}

	private void doWallRepulsion() {
		WallRepulsion wr = new WallRepulsion(mywallarray, this.x, this.y, Forces.walls);
		ArrayList<double[]> wallForces = wr.wallRepulsion();
		for(double[] wallForce : wallForces){
			setForce(wallForce[0],wallForce[1]);
		}
	}
	
	public void setIsFixed(boolean fixed){
		isFixed = fixed;
	}

	@Override
	public void hit (JGObject other) {
		// we hit something! bounce off it!
		Vec2 velocity = myBody.getLinearVelocity();
		final double DAMPING_FACTOR = .7;
		boolean isSide = other.getBBox().height > other.getBBox().width;
		if (isSide) 
			velocity.x *= -DAMPING_FACTOR;
		else 
			velocity.y *= -DAMPING_FACTOR;

		myBody.setLinearVelocity(velocity);
	}


	protected double getDistanceBetween(Mass start, double[] centerOfMass){
		Vec2 startpos = start.getBody().getPosition();
		return Math.sqrt(Math.pow(centerOfMass[0] - startpos.x, 2) + Math.pow(centerOfMass[1] - startpos.y,2));
	}




}
