package forces;
import java.util.List;
import jboxGlue.PhysicalObject;
import Objects.Mass;

public class WallRepulsion{
	private PhysicalObject[] myWallArray;
	private Mass myMass;
	private List<String[]> myWalls;

	protected WallRepulsion(PhysicalObject[] wallarray, Mass mass,List<String[]> walls){
		myWallArray = wallarray;
		myMass = mass;
		myWalls = walls;
	}

	public void doForce(PhysicalObject mass, int i){
		int mag = Integer.parseInt(Environment.walls.get(i)[2]);
		double exp = Double.parseDouble(Environment.walls.get(i)[0]);
		double[] vector = setVector(i);
		double dist = getDistanceBetween(myWallArray[i], i);
		double scale = Math.pow(dist, exp);
		mass.setForce(vector[0] * mag / scale, vector[1] * mag / scale);
	}


	public double[] setVector(int wall){	
		if(wall==0)return new double[] {0,1};
		if(wall==1)return new double[] {-1,0};
		if(wall==2)return new double[] {0,-1};
		if(wall==3)return new double[] {1,0};
		return null;
	}


	private double getDistanceBetween(PhysicalObject wall,int whichwall){
		double wallpos=0;
		double masspos=0;	

		if(whichwall == 0 || whichwall==2)masspos = myMass.y;
		if(whichwall == 2) wallpos = wall.getBody().getPosition().y - 15;
		if(whichwall == 0)wallpos = wall.getBody().getPosition().y + 15;

		if(whichwall ==1 || whichwall==3)masspos = myMass.x;
		if(whichwall == 1)wallpos = wall.getBody().getPosition().x - 15;
		if(whichwall == 3)wallpos = wall.getBody().getPosition().x + 15;

		double diff = Math.abs(wallpos - masspos);
		if((diff) < 1)return 1;
		return diff;

	}
}
