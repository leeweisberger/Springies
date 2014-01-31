package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

public class Spring extends PhysicalObjectRect{
	private PhysicalObject mySpring;
	 Mass myM1;
	 Mass myM2;
	public Spring(Mass m1, Mass m2, double rlength, double springiness){
		 
		 super(m1.toString()+m2.toString(), 2, JGColor.red, 2, rlength);
		 System.out.println(m1.getPosition()[0]);
		 System.out.println(m1.getPosition()[1]);
		 mySpring = new PhysicalObjectRect(m1.toString()+m2.toString(), 2, JGColor.red, 1.0, rlength); 
		 mySpring.setPos(m1.getPosition()[0], m1.getPosition()[1]);
		 myM1=m1; myM2=m2;
		
	}
	
	

}
