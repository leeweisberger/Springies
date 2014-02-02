package springies;

import jboxGlue.PhysicalObjectCircle;
import jgame.JGColor;

public class FixedMass extends PhysicalObjectCircle{
	private String myID;
	private static JGColor myColor = JGColor.blue;
	public FixedMass(String id, int xpos, int ypos){
		super(id, 1, myColor,5,1);
		setPos(xpos, ypos);
		x=xpos;
		y=ypos;
		myID = id;
	}
		
	
	@Override
	public void move(){
		
	}
	
	protected String getID(){
		return myID;
	}
	
	

}
