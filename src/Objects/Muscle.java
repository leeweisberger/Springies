package Objects;

import springies.Model;
import jgame.JGColor;

public class Muscle extends Spring {
	private double myAmplitude;
	private double myRestLength;
	private Mass myM1;
	private Mass myM2;
	private int timer = 0;
	private static JGColor myColor = JGColor.green;
	public Muscle(Mass m1, Mass m2, double originalRLength, double springiness, double amplitude){
		
		super(m1,m2,originalRLength,springiness);
		setColor(myColor);
		myAmplitude = amplitude;
		myRestLength = originalRLength;
		myM1=m1;
		myM2=m2;
	}
	
	@Override
	public void move(){
		springForces();
		timer++;
		setLength((myRestLength * myAmplitude * Math.sin(timer * (30 + (Math.PI / 2)))));
		
	}
	@Override
	public void paintShape(){
		//System.out.println(myRestLength + " , " + (myRestLength * myAmplitude  * Math.sin(30 + (Math.PI / 2))));
		if(myRestLength<(myRestLength * myAmplitude  * Math.sin(timer * (30 + (Math.PI / 2)))))
			myEngine.setColor(JGColor.green);
		else{
			myEngine.setColor(JGColor.blue);
		}
		
		//myEngine.setColor(JGColor.blue);
		
		myEngine.drawLine(myM1.x, myM1.y, myM2.x, myM2.y);
	}
}
