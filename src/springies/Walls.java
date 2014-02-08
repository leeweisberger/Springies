package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;
import jgame.platform.JGEngine;

public class Walls{
	private double myWidth;
	private double myHeight;
	public static PhysicalObject[] wallarray = new PhysicalObjectRect[4];
	private PhysicalObject wallt;
	public Walls(double width, double height ){
		myWidth=width;
		myHeight=height;
	}
	
	public void addWalls()
	{
		// add walls to bounce off of
		// NOTE: immovable objects must have no mass

		final double WALL_MARGIN = 10;
		final double WALL_THICKNESS = 10;
		final double WALL_myWidth = myWidth - WALL_MARGIN * 2 + WALL_THICKNESS;
		final double WALL_myHeight = myHeight - WALL_MARGIN * 2 + WALL_THICKNESS;
		//top wall
		wallt = new PhysicalObjectRect("wall", 2, JGColor.green,
				WALL_myWidth, WALL_THICKNESS);
		wallt.setPos(myWidth / 2, WALL_MARGIN);
		wallarray[0]=wallt;
		//bottom wall
		PhysicalObject wallb = new PhysicalObjectRect("wall", 2, JGColor.green,
				WALL_myWidth, WALL_THICKNESS);
		wallb.setPos(myWidth / 2, myHeight - WALL_MARGIN);
		wallarray[2]=wallb;
		//left wall
		PhysicalObject walll = new PhysicalObjectRect("wall", 2, JGColor.green,
				WALL_THICKNESS, WALL_myHeight);
		walll.setPos(WALL_MARGIN, myHeight / 2);
		wallarray[3]=walll;
		//right wall
		PhysicalObject wallr = new PhysicalObjectRect("wall", 2, JGColor.green,
				WALL_THICKNESS, WALL_myHeight);
		wallr.setPos(myWidth - WALL_MARGIN, myHeight / 2);
		wallarray[1]=wallr;
	}
	
	public PhysicalObject[] getWalls(){
		return wallarray;
	}
	
	public void moveLeft(){
		wallarray[0].setPos(wallarray[0].x, wallarray[0].y+1);
		wallarray[1].setPos(wallarray[1].x-1, wallarray[1].y);
		wallarray[2].setPos(wallarray[2].x, wallarray[2].y-1);
		wallarray[3].setPos(wallarray[3].x+1, wallarray[3].y);
		
		
		
	}
}
