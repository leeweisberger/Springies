package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;

import org.jbox2d.common.Vec2;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;
import java.math.*;

class Mass extends PhysicalObjectCircle{
	
	public Mass(String id, int xpos, int ypos){
		super(id, 1, JGColor.red,5,5);
		setPos(xpos, ypos);
		x=xpos;
		y=ypos;
	}
	
	@Override
	public void hit (JGObject other)
	{
		// we hit something! bounce off it!
		Vec2 velocity = myBody.getLinearVelocity();
		// is it a tall wall?
		final double DAMPING_FACTOR = .4;
		boolean isSide = other.getBBox().height > other.getBBox().width;
		if (isSide) {
			velocity.x *= -DAMPING_FACTOR;
		}
		else {
			velocity.y *= -DAMPING_FACTOR;
		}
		// apply the change
		myBody.setLinearVelocity(velocity);
	}
	
}
