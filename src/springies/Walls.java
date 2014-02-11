package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;
import jgame.platform.JGEngine;

public class Walls{
	private static final int WALLOFFSET = 100;
	private double myWidth;
	private double myHeight;
	private PhysicalObject[] myWallArray = new PhysicalObjectRect[4];
	private PhysicalObject wallt;
	private final double WALL_MARGIN = 10;
	private final double WALL_THICKNESS = 10;
	
	public Walls(double width, double height ){
		myWidth=width;
		myHeight=height;
	}
	
	public void addWalls()
	{	
		final double WALL_myWidth = myWidth - WALL_MARGIN * 2 + WALL_THICKNESS;
		final double WALL_myHeight = myHeight - WALL_MARGIN * 2 + WALL_THICKNESS;
		//top wall
		wallt = new PhysicalObjectRect("wall", 2, JGColor.magenta,
				WALL_myWidth, WALL_THICKNESS);
		wallt.setPos(myWidth / 2, WALL_MARGIN+WALLOFFSET);
		myWallArray[0]=wallt;
		//bottom wall
		PhysicalObject wallb = new PhysicalObjectRect("wall", 2, JGColor.magenta,
				WALL_myWidth, WALL_THICKNESS);
		wallb.setPos(myWidth / 2, myHeight - WALL_MARGIN-WALLOFFSET);
		myWallArray[2]=wallb;
		//left wall
		PhysicalObject walll = new PhysicalObjectRect("wall", 2, JGColor.magenta,
				WALL_THICKNESS, WALL_myHeight);
		walll.setPos(WALL_MARGIN/2+WALLOFFSET, myHeight / 2);
		myWallArray[3]=walll;
		//right wall
		PhysicalObject wallr = new PhysicalObjectRect("wall", 2, JGColor.magenta,
				WALL_THICKNESS, WALL_myHeight);
		wallr.setPos(myWidth - WALL_MARGIN-WALLOFFSET, myHeight / 2);
		myWallArray[1]=wallr;
	}
	
	public PhysicalObject[] getWalls(){
		return myWallArray;
	}
	
	public void moveWalls(boolean isSmaller){
		int scale = -1;
		if(isSmaller) scale=1;
		if(myWallArray[0].y>WALL_THICKNESS || scale==1) myWallArray[0].setPos(myWallArray[0].x, myWallArray[0].y+scale);
		if(myWallArray[1].x<myWidth || scale==1)myWallArray[1].setPos(myWallArray[1].x-scale, myWallArray[1].y);
		if(myWallArray[2].y<(myHeight) || scale==1)myWallArray[2].setPos(myWallArray[2].x, myWallArray[2].y-scale);
		if(myWallArray[3].x>WALL_THICKNESS || scale==1)myWallArray[3].setPos(myWallArray[3].x+scale, myWallArray[3].y);
		
	}
}
