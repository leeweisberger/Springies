package springies;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectRect;
import jboxGlue.WorldManager;
import jgame.platform.JGEngine;


@SuppressWarnings("serial")
public class Springies extends JGEngine

{
	MouseMass mouseMass;
	Spring springMass;
	boolean mouse;
	static int myMouseX;
	static int myMouseY;
	static int gravToggle=1;
	static int viscToggle=1;
	static int massToggle=1;
	static int[] wallToggle = new int[] {1,1,1,1};
	static double muscleToggle=1;
	PhysicalObject[] wallarray = new PhysicalObjectRect[4];
	static HashMap<String,Mass> m = new HashMap<String,Mass>();
	public Springies ()
	{
		// set the window size
		int height = 800;
		double aspect = 16.0 / 9.0;
		initEngineComponent((int) (height * aspect), height);
	}

	@Override
	public void initCanvas ()
	{
		
		
		// I have no idea what tiles do...
		setCanvasSettings(1, // width of the canvas in tiles
				1, // height of the canvas in tiles
				displayWidth(), // width of one tile
				displayHeight(), // height of one tile
				null,// foreground colour -> use default colour white
				null,// background colour -> use default colour black
				null); // standard font -> use default font
	}



	public double[] centerOfMass(){
		double totalx = 0;
		double totaly = 0;
		double totalMass = 0;
		for (String l: m.keySet()){
			totalMass += m.get(l).getMass();
			totalx += (m.get(l).x * m.get(l).getMass());
			totaly += (m.get(l).y * m.get(l).getMass());
		}
		double[] myCenter = {(totalx /(totalMass)), (totaly /(totalMass))};
		return myCenter;
	}

	@Override
	public void initGame ()
	{
		setFrameRate(60, 2);
		WorldManager.initWorld(this);
		//		WorldManager.getWorld().setGravity(new Vec2(0.0f, 0.2f));
		XML_Parser p = new XML_Parser();
		p.parse();
		Walls w = new Walls(displayWidth(), displayHeight());
		wallarray=w.addWalls();
		GetForces f = new GetForces();
		f.getEnvironment();
		addMasses(p);
		addSprings(p);
		//myMouseListener = new MouseListener(this);
		
		
	}
			
	public void addMasses (XML_Parser p)
	{		
		HashMap<String,Double[]> masseslist = new HashMap<String,Double[]>();
		masseslist=p.masses;
		for(String mass:masseslist.keySet()){   			
			if(masseslist.get(mass)[5]!=null){				
				m.put(mass, new FixedMass(mass,masseslist.get(mass)[0],masseslist.get(mass)[1]));
			}
			else{
				m.put(mass, new Mass(mass,masseslist.get(mass)[0],masseslist.get(mass)[1],masseslist.get(mass)[2],masseslist.get(mass)[3],masseslist.get(mass)[4],wallarray));	
			}
		}
	}

	public void addSprings(XML_Parser p){
		
		ArrayList<String[]> springslist=p.springs;  
		for(String[] spring:springslist){  
			Mass m1 = m.get(spring[0]);
			Mass m2 = m.get(spring[1]);			
			PhysicalObject sp = new Spring(m1, m2, Double.parseDouble(spring[3]), Double.parseDouble(spring[2]));
		}
	}

	public void addMuscles(XML_Parser p){		
		ArrayList<String[]> muscleslist=p.muscles;
		for(String[] muscle: muscleslist){
			Mass m1 = m.get(muscle[0]);			
			Mass m2 = m.get(muscle[2]);
			PhysicalObject muscleObj = new Muscle(m1, m2, Double.parseDouble(muscle[3]), Double.parseDouble(muscle[4]), Double.parseDouble(muscle[1]));
		}
	}
	
	public void getMouse(){
		myMouseX = getMousePos().x;
		myMouseY = getMousePos().y;
	}
	@Override
	public void doFrame ()
	
	
	{
		
		makeMouseMass();
		
		
		
//		if(this.getMouseButton(1))
//			getClosestMass(getMousePos().x,getMousePos().y);
		//System.out.println(myMouseListener.length);
		// update game objects
		WorldManager.getWorld().step(1f, 1);
		moveObjects();
		checkCollision(2,1);		
		toggleForces();
		
	}

	private void makeMouseMass() {
		getMouse();
		if(getMouseButton(1) && (mouseMass==null || !mouseMass.isAlive())){
			//clearMouseButton(1);
			mouseMass = new MouseMass("mouse", myMouseX, myMouseY);
			Mass h = getClosestMass(myMouseX,myMouseY);
			double d = getDist(h,myMouseX,myMouseY);
			springMass=new Spring(h, mouseMass, d, .1);
			mouse=true;

		}
		if(mouse && !getMouseButton(1) && mouseMass.isAlive()){
			mouseMass.remove();
			springMass.remove();
			mouse=false;
		}
	}
	public double getDist(Mass m, int xpos, int ypos){
		return Math.sqrt(Math.pow(xpos - m.x, 2) + Math.pow(ypos - m.y,2));
	}
	public Mass getClosestMass(int xpos, int ypos){
		String closest = "";
		double minDist = 10000;
		double dist;
		for(String mass:m.keySet()){
			dist=Math.sqrt(Math.pow(xpos - m.get(mass).x, 2) + Math.pow(ypos - m.get(mass).y,2));
			if(dist<minDist){
				minDist=dist;
				closest=mass;
			}
		}
		return m.get(closest);
	
		//new Spring(m.get(closest),getMousePos(), minDist);
	}

	private void toggleForces() {
		if(getKey('G')){
			clearKey('G');
			gravToggle*=-1;
		}
		if(getKey('V')){
			clearKey('V');
			viscToggle*=-1;
		}
		if(getKey('M')){
			clearKey('M');
			massToggle*=-1;
		}
		//bottom wall
		if(this.getKey('3')){
			clearKey('3');
			wallToggle[2]*=-1;
		}
		//left wall
		if(this.getKey('4')){
			clearKey('4');
			wallToggle[3]*=-1;
		}
		//top wall
		if(this.getKey('1')){
			clearKey('1');
			wallToggle[0]*=-1;
		}
		//right wall
		if(this.getKey('2')){
			clearKey('2');
			wallToggle[1]*=-1;
		}
		if(this.getKey('=')){
			muscleToggle+=.01;
		}
		if(this.getKey('-')){
			if(muscleToggle>0)muscleToggle-=.01;
		}
	}

	@Override
	public void paintFrame ()
	{
		paintToggles();
	}

	private void paintToggles() {
		if(gravToggle==1)drawString("Gravity On",displayWidth()/18, displayHeight()/15, -1);
		if(viscToggle==1)drawString("Viscosity On",displayWidth()/18, displayHeight()/9, -1);
		if(massToggle==1)drawString("Center of Mass On",displayWidth()/18, displayHeight()/6, -1);
		drawString("Walls that Repel: ", displayWidth()/18, displayHeight()/4.5,-1);
		for(int i=0;i<wallToggle.length;i++){
			if(wallToggle[i]==1){
				drawString((i+1)+", ", displayWidth()/18+350 + i*50,displayHeight()/4.5,-1);
			}
		}
		drawString("Muscles at " + muscleToggle + " times original power",displayWidth()/18,displayHeight()/3.5,-1);
	}
}
