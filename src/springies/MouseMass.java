package springies;

public class MouseMass extends FixedMass{

	public MouseMass(String id, double xpos, double ypos) {
		super(id, xpos, ypos);
		// TODO Auto-generated constructor stub
	}
	public void move(){
		x=Springies.myMouseX;
		y=Springies.myMouseY;
	}
	

}
