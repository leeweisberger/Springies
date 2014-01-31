package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;

public class Spring {
	public Spring(Mass m1, Mass m2, double rlength, double springiness){
		 PhysicalObject spring = new PhysicalObjectRect(m1.toString()+m2.toString(), 2, JGColor.green, 10, rlength); 
		 spring.setPos(m1.getPosition()[0], m1.getPosition()[1]);
	}

}
