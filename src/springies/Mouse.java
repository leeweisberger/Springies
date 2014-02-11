package springies;

import Objects.Mass;
import Objects.MouseMass;
import Objects.Spring;


public class Mouse {

	private Model mySpringies;
	private  static MouseMass mouseMass;
	private  static Spring springMass;
	private static boolean mouse;
	private int myMouseX;
	private int myMouseY;
	private static Factory myAssembly;

	public Mouse(Model springies, Factory assembly){
		mySpringies = springies;
		myAssembly = assembly;
	}

	public void makeMouseMass() {
		setMouse();
		if(mySpringies.getMouseButton(1) && (mouseMass==null || !mouseMass.isAlive())){
			mouseMass = new MouseMass("mouse", this);
			Mass h = getClosestMass(myMouseX, myMouseY);
			double d = getDist(h, myMouseX, myMouseY);
			springMass = new Spring(h, mouseMass, d, Double.parseDouble(XML_Parser.SPRINGINESS)*2);
			mouse = true;
		}
		if(mouse && !mySpringies.getMouseButton(1) && mouseMass.isAlive()){
			mouseMass.remove();
			springMass.remove();
			mouse=false;

		}
	}

	private Mass getClosestMass(int xpos, int ypos){
		Mass closest = null;
		double minDist = 10000;
		double dist;
		for(Factory model:mySpringies.getMyAssemblyList()){
			for(Mass mass:model.getMassList()){
				dist=Math.sqrt(Math.pow(xpos - mass.x, 2) + Math.pow(ypos - mass.y,2));
				if(dist<minDist){
					minDist=dist;
					closest=mass;
				}
			}
		}
		return closest;
	}

	private double getDist(Mass m, int xpos, int ypos){
		return Math.sqrt(Math.pow(xpos - m.x, 2) + Math.pow(ypos - m.y,2));
	}
	private void setMouse(){
		myMouseX = mySpringies.getMousePos().x;
		myMouseY = mySpringies.getMousePos().y;
	}
	public int[] getMouse(){
		setMouse();
		return new int[] {myMouseX, myMouseY};
	}
}
