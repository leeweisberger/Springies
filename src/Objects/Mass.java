package Objects;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;

import org.jbox2d.common.Vec2;

import forces.DoForces;

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

	public Mass(String id, double xpos, double ypos,double xvel,double yvel,double mass,PhysicalObject[] wallarray){
		super(id, 1, myColor, 5,mass);
		x=(int)xpos+500;
		y=(int)ypos;
		setPos(x, y);
		setSpeed(xvel, yvel);
		myID = id;
		mywallarray=wallarray;
		myMass = mass;
	}

	public Mass(String id, double xpos, double ypos){
		super(id, 1, myColor, 5,1);
	}

	public String getID(){
		return myID;
	}

	public double getMass(){
		return myMass;
	}


	private void setJGamePosition() {
		if (myBody.m_world != WorldManager.getWorld()) {
			remove();
			return;
		}
		Vec2 position = myBody.getPosition();
		x = position.x;
		y = position.y;
		myRotation = -myBody.getAngle();
	}
	
	public void setIsFixed(boolean fixed){
		isFixed = fixed;
	}
	
	public Vec2 getVec(){
		Vec2 vel = myBody.getLinearVelocity();
		return vel;
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

}
