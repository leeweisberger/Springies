package forces;
import java.util.List;

import jboxGlue.PhysicalObject;
import Objects.Mass;

public class WallRepulsion extends GlobalForce{
	private PhysicalObject[] myWallArray;
	private int myWhichWall;
	private List<String[]> myWallForces;
	protected WallRepulsion(PhysicalObject[] wallarray,int whichWall, List<String[]> wallForces){
		myWallArray = wallarray;
		myWhichWall=whichWall;
		myWallForces = wallForces;
	}

	public void doForce(PhysicalObject mass){
		if(getToggle()){
			int mag = Integer.parseInt(myWallForces.get(myWhichWall)[2]);
			double exp = Double.parseDouble(myWallForces.get(myWhichWall)[0]);
			double[] vector = setVector(myWhichWall);
			double dist = getDistanceBetween(myWallArray[myWhichWall], myWhichWall,mass);
			double scale = Math.pow(dist, exp);
			mass.setForce(vector[0] * mag / scale, vector[1] * mag / scale);
		}
	}

	public double[] setVector(int wall){	
		if(wall==0)return new double[] {0,1};
		if(wall==1)return new double[] {-1,0};
		if(wall==2)return new double[] {0,-1};
		if(wall==3)return new double[] {1,0};
		return null;
	}


	private double getDistanceBetween(PhysicalObject wall,int whichwall, PhysicalObject mass){
		double wallpos=0;
		double masspos=0;	

		if(whichwall == 0 || whichwall==2)masspos = mass.y;
		if(whichwall == 2) wallpos = wall.getBody().getPosition().y - 15;
		if(whichwall == 0)wallpos = wall.getBody().getPosition().y + 15;

		if(whichwall ==1 || whichwall==3)masspos = mass.x;
		if(whichwall == 1)wallpos = wall.getBody().getPosition().x - 15;
		if(whichwall == 3)wallpos = wall.getBody().getPosition().x + 15;

		double diff = Math.abs(wallpos - masspos);
		if((diff) < 1)return 1;
		return diff;

	}
}
