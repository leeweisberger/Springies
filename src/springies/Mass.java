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
	private static JGColor myColor = JGColor.blue;
	public Mass(String id, int xpos, int ypos){
		super(id, 1, myColor,5,1);
		setPos(xpos, ypos);
		x=xpos;
		y=ypos;
		myID = id;
	}
	
	protected String getID(){
		return myID;
	}
<<<<<<< HEAD
=======
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
	}
>>>>>>> 77e3aa37dc9f6ac866fdfb83e23ef33720239835
	
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


}
