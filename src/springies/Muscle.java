package springies;

import jgame.JGColor;

public class Muscle extends Spring {
	private double myAmplitude;
	private double myRestLength;
	private int time;
	public Muscle(Mass m1, Mass m2, double originalRLength, double springiness, double amplitude){
		super(m1,m2,originalRLength,springiness);
		//setColor(JGColor.red);
		myAmplitude = amplitude;
		myRestLength = originalRLength;
		System.out.println(amplitude);
	}
	
	@Override
	public void move(){
		attachMasses();
		setLength((myRestLength * myAmplitude  *100* Math.sin(30 + (Math.PI / 2))));
	}	
}