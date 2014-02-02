package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;

import org.jbox2d.common.Vec2;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

import java.math.*;


public class Mass extends FixedMass{
	private String myID;
	private PhysicalObject[] mywallarray;
	public Mass(String id, int xpos, int ypos,PhysicalObject[] wallarray){
		
		super(id, xpos,ypos);
		setPos(xpos, ypos);
		x=xpos;
		y=ypos;
		myID = id;
		mywallarray=wallarray;
	}
	public String getID(){
		return myID;
	}
	@Override
	public void move(){
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
        wallRepulsion();
        
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
//	public void gravity(){
//		final double GRAVITY = 20;
//		Vec2 velocity = myBody.getLinearVelocity();	
//		if(velocity.y<=0){
//			System.out.println("down");
//			setForce(0, 10);
//		}
//	}
	
	public void wallRepulsion(){
		double WALLREPULSION=40;
		for(int i=0;i<mywallarray.length;i++){
			double dist = getDistanceBetween(this,mywallarray[i],i);
			double force = WALLREPULSION/(dist*dist);
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
