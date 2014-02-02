package springies;

public class Muscle extends Spring {
	private double myAmplitude;
	private double myRestLength;
	private int time;
	public Muscle(Mass m1, Mass m2, double originalRLength, double springiness, double amplitude){
		super(m1,m2,originalRLength,springiness);
		myAmplitude = amplitude;
		myRestLength = originalRLength;
	}
	
	@Override
	public void move(){
		attachMasses();
		this.setLength(myRestLength * myAmplitude);
		Math.cos(time * 2* Math.PI / 60);
		time++;
	}
	
}
