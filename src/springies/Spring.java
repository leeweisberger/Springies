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
	private Mass myM1;
	private Mass myM2;
	private double kValue;
	private double springLength;

	public Spring(Mass m1, Mass m2, double rlength, double springiness){

		super(m1.toString()+m2.toString(), 3, JGColor.red, 1, rlength);
		setPos((m1.x + m2.x), (m1.y+m2.x));
		//		setForce((m1.x + m2.x)/2, (m1.y+m2.x)/2);
		//System.out.println(x);
		myM1=m1;
		myM2=m2;
		//System.out.println(m1.getID());
		//System.out.println(m2.getID());
		//System.out.println(m1.getID() + " : " + m2.getID());
		springLength = rlength;
		kValue = springiness;
//		paint();
	}
//	public ArrayList<Mass> getConnectedto(){
//}
	
	@Override
	public void move(){
		springForces();
	}
	
	protected void springForces(){
//		System.out.println()
		double displacement = (springLength - getDistanceBetween(myM1,myM2));
//		System.out.println(getDistanceBetween(myM1,myM2));
		
		double force = (kValue *  displacement);
		double angle = getAngleBetween(myM1,myM2);
		//System.out.println(force + "force");
		//System.out.println(force);
		double xvector = Math.sin(angle) * force;
		double yvector = Math.cos(angle) * force;
		myM1.setForce(-xvector,-yvector);
		//myM2.setForce(-xvector,-yvector);
		//System.out.println("disp: " + getDistanceBetween(myM1,myM2));
	}

	protected double getDistanceBetween(Mass start, Mass end){	
		//System.out.println(start.getID() + " : " + end.getID() + " : " +Math.sqrt(Math.pow(endpos.x - startpos.x, 2) + Math.pow(endpos.y - startpos.y,2)));
		//System.out.println();
		//System.out.println(" " + start.getID() + "  " + end.getID() + " " + Math.sqrt(Math.pow(endpos.x - startpos.x, 2) + Math.pow(endpos.y - startpos.y,2)));
		return Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y,2));
	}
	
	protected double getAngleBetween(Mass start, Mass end){
		Vec2 startpos = start.getBody().getPosition();
		Vec2 endpos = end.getBody().getPosition();
		return (Math.atan2(endpos.x - startpos.x, endpos.y - startpos.y));
	}
	
	protected double getLength(){
		return springLength;
	}

	protected void setLength(double length){
		springLength = length;
	}
	
	@Override
	public void paintShape(){
		myEngine.setColor(JGColor.blue);
		myEngine.drawLine(myM1.x, myM1.y, myM2.x, myM2.y);
	}
}
