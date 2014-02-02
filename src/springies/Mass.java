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
//		for(PhysicalObject wall:mywallarray){
//			System.out.println(getDistanceBetween(this,wall));
//		}
		int WALLREPULSION = 40;
		double dist = getDistanceBetween(this,mywallarray[1]);
		double force = WALLREPULSION/(dist*dist);
		setForce(0, -force);
		System.out.println("force: " + force);
	}
	protected double getDistanceBetween(PhysicalObject start, PhysicalObject end){
		
		double wally = end.getBody().getPosition().y-15;
		double massy = start.getBody().getPosition().y;
		
		System.out.println(wally-massy);
		if((wally-massy)<1)return 1;
		return wally-massy;
	}


}
