package springies;

import java.util.ArrayList;
import java.util.HashMap;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.PhysicalObjectRect;
import jboxGlue.WorldManager;
import jgame.JGColor;
import jgame.JGObject;
import jgame.platform.JGEngine;

import org.jbox2d.common.Vec2;


@SuppressWarnings("serial")
public class Springies extends JGEngine

{
	PhysicalObject[] wallarray = new PhysicalObjectRect[4];
	private HashMap<String,Mass> m = new HashMap<String,Mass>();
	String[] grav;
	String[] centermass;
	String viscosity;
	ArrayList<String[]> walls = new ArrayList<String[]>();
	public Springies ()
	{
		// set the window size
		int height = 1000;
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

	public Vec2 setGrav() {
		//		Get read in values from XML
		double gravityDirectionRadians = Math.toRadians(90);
//		System.out.println((float)(1 * Math.sin(gravityDirectionRadians)));
//		System.out.println((float)(1 * Math.cos(gravityDirectionRadians)));
		Vec2 gravityVector = new Vec2((float)(20 * Math.cos(gravityDirectionRadians)),(float)(20 * Math.sin(gravityDirectionRadians)));
		return gravityVector;
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
		getEnvironment(p);
		addMasses(p);
		addSprings(p);
	}
		
	public void getEnvironment(XML_Parser p){
		
		ArrayList<String[]> walllist = p.walls;
		grav = p.mygrav;
		centermass = p.mycentermass;
		viscosity = p.myviscosity;	
		walls = p.walls;
		
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
				m.put(mass, new Mass(mass,masseslist.get(mass)[0],masseslist.get(mass)[1],masseslist.get(mass)[2],masseslist.get(mass)[3],masseslist.get(mass)[4],wallarray,this));	
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

	public void addMusclesTest(){
		PhysicalObject muscleone = new Muscle(m.get("m1"),m.get("m2"),100,1,10);
	}

	public void addFixedMassTest(){
		FixedMass first = new FixedMass("m1",400,450);
	}

	@Override
	public void doFrame ()
	{
		// update game objects
		WorldManager.getWorld().step(1f, 1);
		moveObjects();
		checkCollision(2,1);

	}

	@Override
	public void paintFrame ()
	{

	}
}
