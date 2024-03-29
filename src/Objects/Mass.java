package Objects;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;

import org.jbox2d.common.Vec2;



import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

import java.math.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Mass extends PhysicalObjectCircle{
	private static final int MASSOFFSET = 500;
	private String myID;
	private static JGColor myColor = JGColor.yellow;
	private double myMass;
	private boolean isFixed;

	public Mass(String id, double xpos, double ypos,double xvel,double yvel,double mass, JGColor color){
		super(id, 1, myColor, 7,mass);
		setColor(color);
		x=(int)xpos+MASSOFFSET;
		y=(int)ypos;
		setPos(x, y);
		setSpeed(xvel, yvel);
		myID = id;
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
		System.out.println("hit");
	}

}
