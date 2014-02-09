package forces;

import java.io.File;
import java.util.List;

import springies.Factory;
import springies.Springies;
import springies.XML_Parser;

public class Environment {
	public static List<String[]> walls;
	private Gravity gravityEnvironment;
	private Viscosity viscosityEnvironment;
	private CenterOfMass centerOfMassEnvironment;
	private Factory myAssembly;
	private Springies mySpringy;
	
	public Environment(Springies mySpringiesClass){
		super();
		mySpringy = mySpringiesClass;
	}
	
	private void readInEnvironment(){
		File file = new File("environment.xml");
		XML_Parser p = new XML_Parser(file);
		p.parse();
		gravityEnvironment  = new Gravity(p.getGravity());
		viscosityEnvironment = new Viscosity(p.getViscosity());
		centerOfMassEnvironment = new CenterOfMass(p.getCenterMass());
		walls = p.getWalls();
		
	}
	
	public void doToggle(){

		toggleForces('G', gravityEnvironment);
		toggleForces('V', viscosityEnvironment);
		toggleForces('M', centerOfMassEnvironment);
//		toggleForces('3');
//		toggleForces('4');
//		toggleForces('1');
//		toggleForces('2');
//		if(mySpringy.getKey('='))muscleToggle+=.01;
//		if(mySpringy.getKey('-')){
//			if(muscleToggle>0)muscleToggle-=.01;
//		}
		
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
	
	public static void update(){
		
	}
}
