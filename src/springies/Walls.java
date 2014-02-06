package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;
import jgame.platform.JGEngine;

public class Walls{
	double myWidth;
	double myHeight;
	private PhysicalObject[] wallarray = new PhysicalObjectRect[4];
	
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
		PhysicalObject wall = new PhysicalObjectRect("wall", 2, JGColor.green,
				WALL_myWidth, WALL_THICKNESS);
		wall.setPos(myWidth / 2, WALL_MARGIN);
		wallarray[0]=wall;
		//bottom wall
		wall = new PhysicalObjectRect("wall", 2, JGColor.green,
				WALL_myWidth, WALL_THICKNESS);
		wall.setPos(myWidth / 2, myHeight - WALL_MARGIN);
		wallarray[1]=wall;
		//left wall
		wall = new PhysicalObjectRect("wall", 2, JGColor.green,
				WALL_THICKNESS, WALL_myHeight);
		wall.setPos(WALL_MARGIN, myHeight / 2);
		wallarray[2]=wall;
		//right wall
		wall = new PhysicalObjectRect("wall", 2, JGColor.green,
				WALL_THICKNESS, WALL_myHeight);
		wall.setPos(myWidth - WALL_MARGIN, myHeight / 2);
		wallarray[3]=wall;
	}
	
	public PhysicalObject[] getWalls(){
		return wallarray;
	}
}
