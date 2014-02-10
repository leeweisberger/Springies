package Objects;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;
import jgame.JGColor;

public class FixedMass extends Mass{

	
	public FixedMass(String id, double xpos, double ypos){
		super(id, xpos,ypos);
		x=(int)xpos;
		y=(int)ypos;
		setPos(x, y);
		
		setIsFixed(true);		
	}
		
	
	@Override
	public void setForce(double x, double y){
		
		//Do Nothing
	}
	
	

}
