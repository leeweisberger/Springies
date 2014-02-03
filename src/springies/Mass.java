package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;

import org.jbox2d.common.Vec2;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

import java.math.*;
import java.util.HashMap;


public class Mass extends PhysicalObjectCircle{
	private String myID;
	private static JGColor myColor = JGColor.blue;
	private PhysicalObject[] mywallarray;
	private double myMass;
	Springies s;


	public Mass(String id, double xpos, double ypos,double xvel,double yvel,double mass,PhysicalObject[] wallarray, Springies springy){
		super(id, 1, myColor, 5,1);
		x=(int)xpos;
		y=(int)ypos;
		setPos(x, y);
		myID = id;
		mywallarray=wallarray;
		myMass = mass;
		s=springy;
	}

	public Mass(String id, int xpos, int ypos){
		super(id, 1, myColor, 5,1);
	}

	protected String getID(){
		return myID;
	}

	protected double getMass(){
		return myMass;
	}

	@Override
	public void move(){
		//		System.out.println(x + " " + y);
		if (myBody.m_world != WorldManager.getWorld()) {

			remove();
			return;
		}
		// copy the position and rotation from the JBox world to the JGame world
		Vec2 position = myBody.getPosition();
		x = position.x;
		y = position.y;
		myRotation = -myBody.getAngle();
		viscosity();
		//		wallRepulsion();
		//		gravity();
		centerOfMass();
		System.out.println(" njasndjs :     " + s.centerOfMass()[0] + " " + s.centerOfMass()[1]);

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

	public void viscosity(){
		final double VISCOSITY = .8;
		Vec2 velocity = myBody.getLinearVelocity();	
		if(velocity.x>0) setForce(-VISCOSITY, 0);
		if(velocity.x<0) setForce(VISCOSITY, 0);
		if(velocity.y>0) setForce(0,-VISCOSITY);
		if(velocity.y<0) setForce(0, VISCOSITY);

	}
	public void gravity(){
		final double GRAVITY = 20;
		setForce(0,20);
	}

	public void centerOfMass(){
		double[] center = s.centerOfMass();
		double centerX = center[0];
		double centerY = center[1];
		double xDist = this.x - centerX;
		double yDist = this.y - centerY;
		double xForce = 10000/(xDist*xDist);
		double yForce = 10000/(yDist*yDist); 
		if (Math.abs(xDist) < 10){
			xForce = 0;
		}
		if (Math.abs(yDist) < 10){
			yForce = 0;
		}
		//		System.out.println(xForce + " " + yForce);
		if(xDist > 0) setForce(-xForce,0);
		if(xDist < 0) setForce(xForce,0);
		if(yDist > 0) setForce(0,-yForce);
		if(yDist < 0){
			setForce(0,yForce);
			//System.out.println(this.y);
		}

	}




	public void wallRepulsion(){
		//		System.out.println("hi");
		double WALLREPULSION=100;
		for(int i=0;i<mywallarray.length;i++){
			double dist = getDistanceBetween(this,mywallarray[i],i);
			//			System.out.println(dist);
			double force = WALLREPULSION/(dist*dist);
			//			System.out.println(force);
			//			System.out.println(i);
			if(i==0)
				setForce(0, force);						
			else if(i==1)
				setForce(0,-force);
			else if(i==2)
				setForce(force,0);
			else if(i==3)
				setForce(-force,0);							
		}

	}

	protected double getDistanceBetween(Mass start, double[] centerOfMass){
		Vec2 startpos = start.getBody().getPosition();
		return Math.sqrt(Math.pow(centerOfMass[0] - startpos.x, 2) + Math.pow(centerOfMass[1] - startpos.y,2));
	}


	protected double getDistanceBetween(PhysicalObject start, PhysicalObject end,int whichwall){
		double wallpos=0;
		double masspos=0;
		if(whichwall==1) 
			wallpos = end.getBody().getPosition().y-15;
		else if(whichwall==0)wallpos = end.getBody().getPosition().y+15;
		if(whichwall<2)masspos = start.getBody().getPosition().y;
		if(whichwall==2)wallpos = end.getBody().getPosition().x+15;
		if(whichwall==3)wallpos = end.getBody().getPosition().x-15;
		if(whichwall>1)masspos = start.getBody().getPosition().x;
		//System.out.println(wally-massy);
		double diff = Math.abs(wallpos-masspos);
		if((diff)<1)return 1;
		return diff;

	}


}
