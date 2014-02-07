package forces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jboxGlue.PhysicalObject;

public class WallRepulsion extends GlobalForce{
	private PhysicalObject[] myWallArray;
	private double myMassX;
	private double myMassY;
	private List<String[]> myWalls;
	
	protected WallRepulsion(PhysicalObject[] wallarray, double massX, double massY,List<String[]> walls){
		myWallArray = wallarray;
		myMassX = massX;
		myMassY = massY;
		myWalls = walls;
	}
	
	public void doForce(double x, double y, PhysicalObject mass){
		List<double[]> forces = new ArrayList<double[]>();		
		for(int i=0;i<myWallArray.length;i++){
			int mag=0;
			double[] force = new double[2];
			double dist = getDistanceBetween(myWallArray[i],i);
			//ceiling
			
			if(i == 0){
				if(ToggleForces.wallToggle[0] == 1) mag = Integer.parseInt(myWalls.get(0)[2]);
				double scale = Math.pow(dist, Double.parseDouble(myWalls.get(0)[0]));
				force[0] = 0; force[1] = mag/scale;
				forces.add(force);
				mass.setForce(0, mag/scale);	
			}
			
			//floor
			else if(i == 1){
				if(ToggleForces.wallToggle[2]==1)mag = Integer.parseInt(myWalls.get(2)[2]);
				double scale = Math.pow(dist, Double.parseDouble(myWalls.get(2)[0]));
				force[0] = 0; force[1] = -mag/scale;
				forces.add(force);
				mass.setForce(0, -mag/scale);	
			}
			
			//left
			else if(i == 2){
				if(ToggleForces.wallToggle[3] == 1) mag = Integer.parseInt(myWalls.get(3)[2]);
				double scale = Math.pow(dist, Double.parseDouble(myWalls.get(3)[0]));
				force[0] = mag/scale; force[1] = 0;
				forces.add(force);
				mass.setForce(mag/scale,0);	
			}
			
			//right
			else if(i == 3){
				if(ToggleForces.wallToggle[1] == 1)mag = Integer.parseInt(myWalls.get(1)[2]);
				double scale = Math.pow(dist, Double.parseDouble(myWalls.get(1)[0]));
				force[0] = -mag/scale; force[1] = 0;
				forces.add(force);
				mass.setForce(-mag/scale,0);	
			}
		}
	}
	
	private double getDistanceBetween(PhysicalObject end,int whichwall){
		double wallpos=0;
		double masspos=0;	
		if(whichwall == 1) wallpos = end.getBody().getPosition().y - 15;
		else if(whichwall == 0)wallpos = end.getBody().getPosition().y + 15;
		if(whichwall < 2)masspos = myMassY;
		if(whichwall == 2)wallpos = end.getBody().getPosition().x + 15;
		if(whichwall == 3)wallpos = end.getBody().getPosition().x - 15;
		if(whichwall > 1)masspos = myMassX;
		double diff = Math.abs(wallpos - masspos);
		if((diff) < 1)return 1;
		return diff;

	}
}
