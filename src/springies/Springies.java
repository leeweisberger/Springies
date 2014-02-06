package springies;

import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectRect;
import jboxGlue.WorldManager;
import jgame.platform.JGEngine;


@SuppressWarnings("serial")
public class Springies extends JGEngine{

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
		setFrameRate(60, 2);
		WorldManager.initWorld(this);
		//		WorldManager.getWorld().setGravity(new Vec2(0.0f, 0.2f));
		Walls w = new Walls(displayWidth(), displayHeight());
		w.addWalls();
		new GetForces().getEnvironment();;
		addAssembly(w.getWalls());	
	}

	public void addAssembly(PhysicalObject[] wallarray){
		new Assembly(wallarray).addAssembly();
	}

	@Override
	public void doFrame ()
	{
		new Mouse(this).makeMouseMass();
		new ToggleForces(this).toggleForces();	
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
		if(ToggleForces.gravToggle==1)drawString("Gravity On",displayWidth()/18, displayHeight()/15, -1);
		if(ToggleForces.viscToggle==1)drawString("Viscosity On",displayWidth()/18, displayHeight()/9, -1);
		if(ToggleForces.massToggle==1)drawString("Center of Mass On",displayWidth()/18, displayHeight()/6, -1);
		drawString("Walls that Repel: ", displayWidth()/18, displayHeight()/4.5,-1);
		for(int i=0;i<ToggleForces.wallToggle.length;i++){
			if(ToggleForces.wallToggle[i]==1){
				drawString((i+1)+", ", displayWidth()/18+350 + i*50,displayHeight()/4.5,-1);
			}
		}
		drawString("Muscles at " + ToggleForces.muscleToggle + " times original power",displayWidth()/18,displayHeight()/3.5,-1);
	}
}
