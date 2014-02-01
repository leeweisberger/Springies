package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;

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
	
	
	
	
	

	




}
