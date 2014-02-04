package springies;

import java.util.ArrayList;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;

public class WallRepulsion{
	PhysicalObject[] myWallArray;
	double myMassX;
	double myMassY;
	ArrayList<String[]> myWalls;
	public WallRepulsion(PhysicalObject[] wallarray, double massX, double massY,ArrayList<String[]> walls){
		myWallArray = wallarray;
		myMassX = massX;
		myMassY = massY;
		myWalls=walls;
	}
	public ArrayList<double[]> wallRepulsion(){
		//		System.out.println("hi");
		ArrayList<double[]> forces = new ArrayList<double[]>();
		for(int i=0;i<myWallArray.length;i++){
			double[] force = new double[2];
			double dist = getDistanceBetween(myWallArray[i],i);
			//ceiling
			if(i==0){
				int mag = Integer.parseInt(myWalls.get(0)[2]);
				double scale = Math.pow(dist, Double.parseDouble(myWalls.get(0)[0]));
				force[0]=0; force[1]=mag/scale;
				forces.add(force);
				//setForce(0, mag/scale);	
			}
			//floor
			else if(i==1){
				int mag = Integer.parseInt(myWalls.get(2)[2]);
				//System.out.println("mag : " + mag);
				double scale = Math.pow(dist, Double.parseDouble(myWalls.get(2)[0]));
				force[0]=0; force[1]=-mag/scale;
				forces.add(force);
				//System.out.println(force[1]);
				//System.out.println("floor   " + force[1]);
				//setForce(0, -mag/scale);	
			}
			//left
			else if(i==2){
				int mag = Integer.parseInt(myWalls.get(3)[2]);
				double scale = Math.pow(dist, Double.parseDouble(myWalls.get(3)[0]));
				force[0]=mag/scale; force[1]=0;
				forces.add(force);
				//setForce(mag/scale,0);	
			}
			//right
			else if(i==3){
				int mag = Integer.parseInt(myWalls.get(1)[2]);
				double scale = Math.pow(dist, Double.parseDouble(myWalls.get(1)[0]));
				force[0]=-mag/scale; force[1]=0;
				forces.add(force);
				//setForce(-mag/scale,0);	
			}
		}
		return forces;

	}
	protected double getDistanceBetween(PhysicalObject end,int whichwall){
		double wallpos=0;
		double masspos=0;
		if(whichwall==1) 
			wallpos = end.getBody().getPosition().y-15;
		else if(whichwall==0)wallpos = end.getBody().getPosition().y+15;
		if(whichwall<2)masspos = myMassY;
		if(whichwall==2)wallpos = end.getBody().getPosition().x+15;
		if(whichwall==3)wallpos = end.getBody().getPosition().x-15;
		if(whichwall>1)masspos = myMassX;
		//System.out.println(wally-massy);
		double diff = Math.abs(wallpos-masspos);
		if((diff)<1)return 1;
		return diff;

	}
}
