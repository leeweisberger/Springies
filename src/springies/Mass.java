package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;

import org.jbox2d.common.Vec2;

import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

public class Mass extends PhysicalObjectCircle{
	private double xposition;
	private double yposition;
	private String myID;
	private PhysicalObject myMass;
	public Mass(String id, int x, int y){
		super(id, 1, JGColor.blue,10,5);
		
		PhysicalObject mass = new PhysicalObjectCircle(id, 1, JGColor.blue, 10, 5); 
		 mass.setPos(x, y);
		 xposition=x;
		 yposition=y;
		 myID=id;
		 myMass=mass;
	}
	
	@Override
	public void move(){
		//myMass.setPos(xposition, yposition);
	}
	public double[] getPosition(){
		double[] pos = {xposition,yposition};
		return pos;
		
	}
	
	public Mass getMass(){
		return this;
	}
	public String toString(){
		return myID;
	}
	public String getID(){
		return myID;
	}

	
	
	
}
