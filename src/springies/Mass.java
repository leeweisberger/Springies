package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;

import org.jbox2d.common.Vec2;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

public class Mass extends PhysicalObjectCircle{

	public Mass(String id, int xpos, int ypos){
		super(id, 1, JGColor.red,5,5);
		setPos(xpos, ypos);
		x=xpos;
		y=ypos;
		


	}
	 @Override
	    public void move ()
	    {
		 	
	        // if the JGame object was deleted, remove the physical object too
	        if (myBody.m_world != WorldManager.getWorld()) {
	            remove();
	            return;
	        }
	        
	        // copy the position and rotation from the JBox world to the JGame world
	        Vec2 position = myBody.getPosition();
	        x = position.x;
	        y = position.y;
	        //viscosity();
	        myRotation = -myBody.getAngle();
	    }
	@Override
	public void hit (JGObject other)
	{
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
