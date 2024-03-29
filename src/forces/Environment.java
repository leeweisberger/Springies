package forces;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import springies.Factory;
import springies.Model;
import springies.Walls;
import springies.XML_Parser;
import Objects.Mass;
import Objects.Muscle;

public class Environment {
	private List<String[]> wallForces;
	private Gravity gravityEnvironment;
	private Viscosity viscosityEnvironment;
	private CenterOfMass centerOfMassEnvironment;
	private Model mySpringy;
	private WallRepulsion[] wallEnvironments = new WallRepulsion[4];
	private Walls myWalls;
	private double muscleToggle = 1;

	public Environment(Model mySpringiesClass, Walls walls){
		super();
		mySpringy = mySpringiesClass;
		myWalls=walls;
	}

	private void readInEnvironment(){
		File file = new File("environment.xml");
		XML_Parser p = new XML_Parser(file);
		p.parse();
		wallForces = p.getWalls();
		gravityEnvironment  = new Gravity(p.getGravity());
		viscosityEnvironment = new Viscosity(p.getViscosity());
		centerOfMassEnvironment = new CenterOfMass(p.getCenterMass());
		for(int i=0;i<wallEnvironments.length;i++){
			wallEnvironments[i] = new WallRepulsion(myWalls.getWalls(),i,wallForces);
		}
	}

	public void doToggle(){

		toggleForces(KeyEvent.VK_G, gravityEnvironment);
		toggleForces(KeyEvent.VK_V, viscosityEnvironment);
		toggleForces(KeyEvent.VK_M, centerOfMassEnvironment);
		toggleForces(KeyEvent.VK_1,wallEnvironments[0]);
		toggleForces(KeyEvent.VK_2,wallEnvironments[1]);
		toggleForces(KeyEvent.VK_3,wallEnvironments[2]);
		toggleForces(KeyEvent.VK_4,wallEnvironments[3]);
	}

	public void toggleForces(int togglekey, GlobalForce toggledForce){
		if(mySpringy.getKey(togglekey)){
			mySpringy.clearKey(togglekey);
			toggledForce.changeToggle();
		}
	}

	public void initEnvironment(){
		readInEnvironment();

	}

	public void update(Factory factory){
		doToggle();
		if(mySpringy.getKey(KeyEvent.VK_EQUALS)){
			muscleToggle += 0.05;
			for (Muscle myMuscle: factory.getMuscleList()){
				myMuscle.increaseAmplitude();
			}
		}
		if(mySpringy.getKey(KeyEvent.VK_MINUS)){
			if (muscleToggle > 0){
				muscleToggle -= 0.05;
			}
			for (Muscle myMuscle: factory.getMuscleList()){
				myMuscle.decreaseAmplitude();
			}
		}
		for(Mass mass:factory.getMassList()){
			for(WallRepulsion wall:wallEnvironments){
				wall.doForce(mass);
			}
			gravityEnvironment.doForce(mass);
			viscosityEnvironment.doForce(mass);
			centerOfMassEnvironment.doForce(mass,factory);
		}
	}

	public void paintToggles() {

		mySpringy.drawString("Click 'N' to add an assembly",mySpringy.displayWidth()/18, mySpringy.displayHeight()/4.5 + 200, -1);

		if(gravityEnvironment.getToggle())mySpringy.drawString("Gravity On",mySpringy.displayWidth()/18, mySpringy.displayHeight()/15, -1);
		if(viscosityEnvironment.getToggle())mySpringy.drawString("Viscosity On",mySpringy.displayWidth()/18, mySpringy.displayHeight()/9, -1);
		if(centerOfMassEnvironment.getToggle())mySpringy.drawString("Center of Mass On",mySpringy.displayWidth()/18, mySpringy.displayHeight()/6, -1);
		mySpringy.drawString("Walls that Repel: ", mySpringy.displayWidth()/18, mySpringy.displayHeight()/4.5,-1);
		mySpringy.drawString("Muscles at " + muscleToggle + " times original power",mySpringy.displayWidth()/18,mySpringy.displayHeight()/3.5,-1);
		for(int i=0;i<wallEnvironments.length;i++){
			if(wallEnvironments[i].getToggle()){
				mySpringy.drawString((i+1)+" ", mySpringy.displayWidth()/18+350 + i*50,mySpringy.displayHeight()/4.5,-1);
			}
		}
	}

}
