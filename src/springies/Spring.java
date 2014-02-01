package springies;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

public class Spring extends PhysicalObjectRect{
	private PhysicalObject mySpring;
	 private Mass myM1;
	 private Mass myM2;
	public Spring(Mass m1, Mass m2, double rlength, double springiness){
		 
		 super(m1.toString()+m2.toString(), 2, JGColor.green, 1, rlength);
		 float f = (float) 0;
		// setAngle(145);
		 System.out.println();
		 x=m1.x;
		 y=m1.y;
		 setPos((m1.x + m2.x)/2, (m1.y+m2.x)/2);
		 //System.out.println(x);
		 myM1=m1;
		 myM2=m2;
	}
	
	public void move(){
		//System.out.println(myM1.y);
		//System.out.println(x);
	}
	
	

}
