package Objects;

import springies.Mouse;


public class MouseMass extends FixedMass{
	private Mouse myMouse;

	public MouseMass(String id, Mouse mouse) {
		super(id, mouse.getMouse()[0], mouse.getMouse()[1]);
		myMouse=mouse;

	}
	public void move(){
		x=myMouse.getMouse()[0];
		y=myMouse.getMouse()[1];
		System.out.println(y);
	}
}
