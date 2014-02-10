package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;
import jgame.platform.JGEngine;

public class Walls{
	private double myWidth;
	private double myHeight;
	private PhysicalObject[] myWallArray = new PhysicalObjectRect[4];
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
		wallt = new PhysicalObjectRect("wall", 2, JGColor.magenta,
				WALL_myWidth, WALL_THICKNESS);
		wallt.setPos(myWidth / 2, WALL_MARGIN);
		myWallArray[0]=wallt;
		//bottom wall
		PhysicalObject wallb = new PhysicalObjectRect("wall", 2, JGColor.magenta,
				WALL_myWidth, WALL_THICKNESS);
		wallb.setPos(myWidth / 2, myHeight - WALL_MARGIN);
		myWallArray[2]=wallb;
		//left wall
		PhysicalObject walll = new PhysicalObjectRect("wall", 2, JGColor.magenta,
				WALL_THICKNESS, WALL_myHeight);
		walll.setPos(WALL_MARGIN, myHeight / 2);
		myWallArray[3]=walll;
		//right wall
		PhysicalObject wallr = new PhysicalObjectRect("wall", 2, JGColor.magenta,
				WALL_THICKNESS, WALL_myHeight);
		wallr.setPos(myWidth - WALL_MARGIN, myHeight / 2);
		myWallArray[1]=wallr;
	}
	
	public PhysicalObject[] getWalls(){
		return myWallArray;
	}
	
	public void moveWalls(boolean isSmaller){
		int scale = -1;
		if(isSmaller) scale=1;
		myWallArray[0].setPos(myWallArray[0].x, myWallArray[0].y+1*scale);
		myWallArray[1].setPos(myWallArray[1].x-1*scale, myWallArray[1].y);
		myWallArray[2].setPos(myWallArray[2].x, myWallArray[2].y-1*scale);
		myWallArray[3].setPos(myWallArray[3].x+1*scale, myWallArray[3].y);
	}
}
