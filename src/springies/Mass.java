package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;

import org.jbox2d.common.Vec2;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

public class Mass {
	private double xposition;
	private double yposition;
	String myID;
	public Mass(String id, int x, int y){
		 PhysicalObject mass = new PhysicalObjectCircle(id, 1, JGColor.blue, 10, 5); 
		 mass.setPos(x, y);
		 xposition=x;
		 yposition=y;
		 myID=id;
		
	}
	
	public void hit(){
		
	}
	public double[] getPosition(){
		double[] pos = {xposition,yposition};
		return pos;
		
	}
	public Mass getMass(){
		return this;
	}
	public String getID(){
		return myID;
	}
	
	
}
