package springies;


public class MouseMass extends FixedMass{

	public MouseMass(String id, double xpos, double ypos) {
		super(id, xpos, ypos);

	}
	public void move(){
		x=Mouse.myMouseX;
		y=Mouse.myMouseY;
	}
}
