package springies;

import java.util.List;

import jboxGlue.PhysicalObject;
import jboxGlue.WorldManager;
import jgame.platform.JGEngine;
import Objects.Mass;
import forces.DoForces;



@SuppressWarnings("serial")
public class Springies extends JGEngine{
	private Walls myWalls;
	public static boolean gravToggle=true;
	public static boolean viscToggle=true;
	public static boolean massToggle=true;
	public static boolean[] wallToggle = new boolean[] {true,true,true,true};
	public static double muscleToggle=1;
	private Assembly myAssembly;
	private List<Mass> myMassList;
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

	@Override
	public void initGame ()
	{
		setFrameRate(60, 4);
		WorldManager.initWorld(this);
		//		WorldManager.getWorld().setGravity(new Vec2(0.0f, 0.2f));
		myWalls = new Walls(displayWidth(), displayHeight());
		myWalls.addWalls();
		new GetForces().getEnvironment();
		addAssembly(myWalls.getWalls());	
	}

	public void addAssembly(PhysicalObject[] wallarray){
		myAssembly = new Assembly(wallarray);
		myAssembly.addAssembly();
		
	}

	@Override
	public void doFrame ()
	{
		new DoForces(this,myAssembly).doForces();
		doToggle();
		
		if(getKey('N')){
			clearKey('N');
			addAssembly(myWalls.getWalls());
		}
		
		if(getKey('Y')){
			clearKey('Y');
			myWalls.moveLeft();
		}
		
		new Mouse(this).makeMouseMass();
		doToggle();
		WorldManager.getWorld().step(1f, 1);
		moveObjects();
		checkCollision(2,1);	 
	}

	@Override
	public void paintFrame ()
	{
		paintToggles();
	}
	
	private void paintToggles() {
		drawString("Click 'N' to add an assembly",displayWidth()/18, displayHeight()/4.5 + 200, -1);
		if(gravToggle)drawString("Gravity On",displayWidth()/18, displayHeight()/15, -1);
		if(viscToggle)drawString("Viscosity On",displayWidth()/18, displayHeight()/9, -1);
		if(massToggle)drawString("Center of Mass On",displayWidth()/18, displayHeight()/6, -1);
		drawString("Walls that Repel: ", displayWidth()/18, displayHeight()/4.5,-1);
		for(int i=0;i<wallToggle.length;i++){
			if(wallToggle[i]){
				drawString((i+1)+", ", displayWidth()/18+350 + i*50,displayHeight()/4.5,-1);
			}
		}
		drawString("Muscles at " + muscleToggle + " times original power",displayWidth()/18,displayHeight()/3.5,-1);
	}
	public boolean toggleForces(int togglekey, boolean force){
		if(getKey(togglekey)){
			
			
			clearKey(togglekey);
			force=!force;
			System.out.println(force);
		}
		return force;
	}
	public void doToggle(){
		
		gravToggle = toggleForces('G',gravToggle);
		viscToggle = toggleForces('V',viscToggle);
		massToggle = toggleForces('M',massToggle);
		wallToggle[2] = toggleForces('3',wallToggle[2]);
		wallToggle[3] = toggleForces('4',wallToggle[3]);
		wallToggle[0] = toggleForces('1',wallToggle[0]);
		wallToggle[1] = toggleForces('2',wallToggle[1]);
		if(getKey('='))muscleToggle+=.01;
		if(getKey('-')){
			if(muscleToggle>0)muscleToggle-=.01;
		}		
	}
}
