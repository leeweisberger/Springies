package springies;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;
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
		
		//if(myID.equals("m1"))System.out.println(this.y);
	}
	public String getID(){
		return myID;
	}
	

}
