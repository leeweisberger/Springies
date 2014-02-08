package forces;

import springies.Springies;
import jgame.platform.JGEngine;

public class ToggleForces{
	private static boolean gravityToggle = true;
	private static boolean viscosityToggle = true;
	private static boolean massToggle = true;
	private static boolean[] wallToggle = new boolean[] {true,true,true,true};
	
	public static boolean getGravityToggle() {
		return gravityToggle;
	}

	public static boolean getViscosityToggle() {
		return viscosityToggle;
	}

	public static boolean getMassToggle() {
		return massToggle;
	}

	public static boolean[] getWallToggle() {
		return wallToggle;
	}

	public static double getMuscleToggle() {
		return muscleToggle;
	}

//	private boolean muscleToggle = true;
//	public static int gravToggle=1;
//	public static int viscToggle=1;
//	public static int massToggle=1;
//	public static int[] wallToggle = new int[] {1,1,1,1};
	public static double muscleToggle=1;
	private Springies mySpringies;
	
	public ToggleForces(Springies springies){
		mySpringies = springies;
	}
	
	public void toggleForces() {
		if(mySpringies.getKey('G')){
			mySpringies.clearKey('G');
			gravityToggle = (!gravityToggle);
		}
		if(mySpringies.getKey('V')){
			mySpringies.clearKey('V');
			viscosityToggle = (!viscosityToggle);
		}
		if(mySpringies.getKey('M')){
			mySpringies.clearKey('M');
			massToggle = (!massToggle);
		}
		//bottom wall
		if(mySpringies.getKey('3')){
			mySpringies.clearKey('3');
			wallToggle[2] = (!wallToggle[2]);
		}
		//left wall
		if(mySpringies.getKey('4')){
			mySpringies.clearKey('4');
			wallToggle[3] = (!wallToggle[3]);
		}
		//top wall
		if(mySpringies.getKey('1')){
			mySpringies.clearKey('1');
			wallToggle[0] = (!wallToggle[0]);
		}
		//right wall
		if(mySpringies.getKey('2')){
			mySpringies.clearKey('2');
			wallToggle[1] = (!wallToggle[1]);
		}
		if(mySpringies.getKey('=')){
			muscleToggle+=.01;
		}
		if(mySpringies.getKey('-')){
			if(muscleToggle>0)muscleToggle-=.01;
		}


	}

}
