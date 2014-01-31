package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;

import org.jbox2d.common.Vec2;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

public class Mass {
	
	public Mass(String id, int x, int y){
		 PhysicalObject ball = new PhysicalObjectCircle("ball", 1, JGColor.blue, 10, 5); 
		 ball.setPos(x, y);
		
	}
	
	public void hit(){
		
	}
	
	
}
