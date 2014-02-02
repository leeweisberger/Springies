package springies;

import jboxGlue.PhysicalObjectCircle;
import jgame.JGColor;

public class FixedMass extends Mass{
	private String myID;
	
	public FixedMass(String id, int xpos, int ypos){
		super(id, xpos,ypos);
		myID = id;
	}
		
	
	@Override
	public void move(){
		
	}
	

}
