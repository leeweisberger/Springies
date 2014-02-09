package springies;

import java.util.ArrayList;
import java.util.List;

import jboxGlue.PhysicalObject;
import jboxGlue.WorldManager;
import jgame.platform.JGEngine;

import forces.Environment;



@SuppressWarnings("serial")
public class Springies extends JGEngine{
	private Walls myWalls;
	private Environment myEnvironment;
//	private boolean gravToggle=true;
//	private boolean viscToggle=true;
//	private boolean massToggle=true;
//	private boolean[] wallToggle = new boolean[] {true,true,true,true};
	public static double muscleToggle=1;
	private List<Factory> myAssemblyList = new ArrayList<Factory>();
	private boolean[] toggles;

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
		
		addAssembly(myWalls.getWalls());
		myEnvironment = new Environment(this,myAssemblyList.get(0));
		myEnvironment.initEnvironment();
//		toggles = new boolean[]{gravToggle,viscToggle,massToggle};
	}

	public void addAssembly(PhysicalObject[] wallarray){

		Factory a = new Factory(wallarray);
		myAssemblyList.add(a);
		a.addAssembly();
	}

	@Override
	public void doFrame ()
	{
		for(Factory a: myAssemblyList){
			myEnvironment.update(a);
			new Mouse(this, a).makeMouseMass();
		}
//		doToggle();

		if(getKey('N')){
			clearKey('N');
			addAssembly(myWalls.getWalls());
		}

		if(getKey(KeyDown)){
			clearKey(KeyDown);
			myWalls.moveWalls(true);
		}
		if(getKey(KeyUp)){
			clearKey(KeyUp);
			myWalls.moveWalls(false);
		}
		WorldManager.getWorld().step(1f, 1);
		moveObjects();
		checkCollision(2,1);	 
	}

//	@Override
//	public void paintFrame ()
//	{
//		paintToggles();
//	}

//	private void paintToggles() {
//		drawString("Click 'N' to add an assembly",displayWidth()/18, displayHeight()/4.5 + 200, -1);
//
//		if(gravToggle)drawString("Gravity On",displayWidth()/18, displayHeight()/15, -1);
//		if(viscToggle)drawString("Viscosity On",displayWidth()/18, displayHeight()/9, -1);
//		if(massToggle)drawString("Center of Mass On",displayWidth()/18, displayHeight()/6, -1);
//		drawString("Walls that Repel: ", displayWidth()/18, displayHeight()/4.5,-1);
//		for(int i=0;i<wallToggle.length;i++){
//			if(wallToggle[i]){
//
//				drawString((i+1)+", ", displayWidth()/18+350 + i*50,displayHeight()/4.5,-1);
//			}
//		}
//		drawString("Muscles at " + muscleToggle + " times original power",displayWidth()/18,displayHeight()/3.5,-1);
//	}
	
	
//	public boolean getGravToggle(){
//		return gravToggle;
//	}
//	public boolean getMassToggle(){
//		return massToggle;
//	}
//	public boolean getViscToggle(){
//		return viscToggle;
//	}
//	public double getMuscleToggle(){
//		return muscleToggle;
//	}
//	public boolean[] getWallToggles(){
//		return wallToggle;
//	}
}
