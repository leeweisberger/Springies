package springies;

import jboxGlue.PhysicalObjectCircle;
import jgame.JGColor;

public class FixedMass extends Mass{
	private String myID;
	
	public FixedMass(String id, double xpos, double ypos){
		super(id, xpos,ypos);
		//System.out.println("fiex");
		x=(int)xpos;
		y=(int)ypos;
		setPos(x, y);
		myID = id;
		
		
	}
		
	
	@Override
	public void move(){
		//System.out.println("fixed: " + myID);
	}
	public String getID(){
		return myID;
	}
	

}
