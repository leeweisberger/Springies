package springies;

import java.util.ArrayList;
import java.util.List;

import jboxGlue.PhysicalObject;
import jboxGlue.WorldManager;
import jgame.JGColor;
import jgame.platform.JGEngine;
import forces.Environment;



@SuppressWarnings("serial")
public class Model extends JGEngine{
	private Walls myWalls;
	private Environment myEnvironment;
	public static double muscleToggle=1;
	private List<Factory> myAssemblyList = new ArrayList<Factory>();
	
	public List<Factory> getMyAssemblyList() {
		return myAssemblyList;
	}

	private boolean[] toggles;

	public Model ()
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

	@Override
	public void initGame ()
	{
		setFrameRate(60, 4);
		WorldManager.initWorld(this);
		//		WorldManager.getWorld().setGravity(new Vec2(0.0f, 0.2f));
		myWalls = new Walls(displayWidth()-100, displayHeight()-100);
		myWalls.addWalls();	
		addAssembly(myWalls.getWalls());
		myEnvironment = new Environment(this,myWalls);
		myEnvironment.initEnvironment();
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

		addNewAssembly();

		moveWalls();
		WorldManager.getWorld().step(1f, 1);
		moveObjects();
		checkCollision(1,2);	 
	}

	private void addNewAssembly() {
		if(getKey('N')){
			clearKey('N');
			addAssembly(myWalls.getWalls());
		}
	}

	private void moveWalls() {
		if(getKey(KeyDown)){
			clearKey(KeyDown);
			myWalls.moveWalls(true);
		}
		if(getKey(KeyUp)){
			clearKey(KeyUp);
			myWalls.moveWalls(false);
		}
	}

	@Override
	public void paintFrame ()
	{
		myEnvironment.paintToggles();
	}
}