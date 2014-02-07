package springies;

import forces.ToggleForces;
import jgame.JGColor;

public class Muscle extends Spring {
	private double myAmplitude;
	private double myRestLength;
	private int time;
	private static JGColor myColor = JGColor.green;
	public Muscle(Mass m1, Mass m2, double originalRLength, double springiness, double amplitude){
		super(m1,m2,originalRLength,springiness);
		setColor(myColor);
		myAmplitude = amplitude * ToggleForces.muscleToggle;
		myRestLength = originalRLength;
	}
	
	@Override
	public void move(){
		springForces();
		setLength((myRestLength * myAmplitude  *100* Math.sin(30 + (Math.PI / 2))));
	}	
}
