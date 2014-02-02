package springies;

import java.util.Vector;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;
import org.jbox2d.common.Vec2;
public class Spring extends PhysicalObjectRect{
	private PhysicalObject mySpring;
	private Mass myM1;
	private Mass myM2;
	private double kValue;
	private double springLength;

	public Spring(Mass m1, Mass m2, double rlength, double springiness){

		super(m1.toString()+m2.toString(), 3, JGColor.green, 1, rlength);
		float f = (float) 0;
		//		System.out.println();
		x=m1.x;
		y=m1.y;
		setPos((m1.x + m2.x), (m1.y+m2.x));
		//		setForce((m1.x + m2.x)/2, (m1.y+m2.x)/2);
		//System.out.println(x);
		myM1=m1;
		myM2=m2;
		springLength = rlength;
		kValue = springiness;
//		paint();
	}

	@Override
	public void move(){
		attachMasses();
	}
	
	private void attachMasses(){
		double mx = myM1.getLastX() - myM2.getLastX();
		System.out.println("mx = " + mx);
		double my = myM1.getLastY() - myM2.getLastY();
		System.out.println("my = " + my);
		double displacement = (getDistanceBetween(myM1,myM2)) - springLength;
		double force = -1 * (kValue * displacement);
		double xvector = force * Math.sin(getAngleBetween(myM1,myM2));
		double yvector = force * Math.cos(getAngleBetween(myM1,myM2));
//		System.out.println(xvector);
//		System.out.println(yvector);
		myM1.setForce(xvector,yvector);
		myM2.setForce(-xvector,-yvector);
	}

	private double getDistanceBetween(Mass start, Mass end){
		double first = (start.x - end.x) * (start.x-end.x);
		double second = (start.y-end.y) * (start.y-end.y);
		return (Math.sqrt((first + second)));
	}
	
	private double getAngleBetween(Mass start, Mass end){
		float angle = (float) (Math.atan2(end.x - start.x, end.y - start.y));
	    if(angle < 0){
	        angle += (Math.PI / 2.0);
	    }
	    return angle;
	}
	
	@Override
	public void paint(){
		double[] myX = {myM1.x,myM2.x};
		double[] myY = {myM1.y,myM2.y};
		JGColor[] myColors = {JGColor.green,JGColor.blue};
		myEngine.drawPolygon(myX, myY, myColors, 2, false, false);
	}
}
