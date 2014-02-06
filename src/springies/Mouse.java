package springies;

public class Mouse {

	private Springies mySpringies;
	private static MouseMass mouseMass;
	private static Spring springMass;
	private static boolean mouse;
	public static int myMouseX;
	public static int myMouseY;
	
	public Mouse(Springies springies){
		mySpringies = springies;
	}
	
	public void makeMouseMass() {
		getMouse();
		if(mySpringies.getMouseButton(1) && (mouseMass==null || !mouseMass.isAlive())){
			//mySpringies.clearMouseButton(1);
			mouseMass = new MouseMass("mouse", myMouseX, myMouseY);
			Mass h = getClosestMass(myMouseX,myMouseY);
			double d = getDist(h,myMouseX,myMouseY);
			springMass=new Spring(h, mouseMass, d, .1);
			mouse=true;


		}
		if(mouse && !mySpringies.getMouseButton(1) && mouseMass.isAlive()){
			mouseMass.remove();
			springMass.remove();
			mouse=false;
			
		}
	}
	public Mass getClosestMass(int xpos, int ypos){
		String closest = "";
		double minDist = 10000;
		double dist;
		for(String mass:Assembly.m.keySet()){
			dist=Math.sqrt(Math.pow(xpos - Assembly.m.get(mass).x, 2) + Math.pow(ypos - Assembly.m.get(mass).y,2));
			if(dist<minDist){
				minDist=dist;
				closest=mass;
			}
		}
		return Assembly.m.get(closest);
	}
	public double getDist(Mass m, int xpos, int ypos){
		return Math.sqrt(Math.pow(xpos - m.x, 2) + Math.pow(ypos - m.y,2));
	}
	public void getMouse(){
		myMouseX = mySpringies.getMousePos().x;
		myMouseY = mySpringies.getMousePos().y;
	}
	
	
}
