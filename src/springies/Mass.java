package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;

import org.jbox2d.common.Vec2;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;
import java.math.*;


public class Mass extends PhysicalObjectCircle{
	private String myID;
	public Mass(String id, int xpos, int ypos){
		super(id, 1, JGColor.red,5,1);
		setPos(xpos, ypos);
		x=xpos;
		y=ypos;
		myID = id;


	}
	public String getID(){
		return myID;
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

//	public void viscosity(){
//		final double VISCOSITY = .8;
//		Vec2 velocity = myBody.getLinearVelocity();	
//		System.out.println("bef: " + velocity.y);
//		velocity.x*=VISCOSITY;
//		velocity.y *= VISCOSITY;
//		
//		System.out.println("aft: " + velocity.y);
//		myBody.setLinearVelocity(velocity);
//		
//		
//	}
//	public void gravity(){
//		final double GRAVITY = 20;
//		Vec2 velocity = myBody.getLinearVelocity();	
//		if(velocity.y<=0){
//			System.out.println("down");
//			setForce(0, 10);
//		}
//	}


}
