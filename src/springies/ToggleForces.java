package springies;

import jgame.platform.JGEngine;

public class ToggleForces{
	static int gravToggle=1;
	static int viscToggle=1;
	static int massToggle=1;
	static int[] wallToggle = new int[] {1,1,1,1};
	static double muscleToggle=1;
	private Springies mySpringies;
	public ToggleForces(Springies springies){
		mySpringies = springies;
	}
	public void toggleForces() {
		if(mySpringies.getKey('G')){
			mySpringies.clearKey('G');
			gravToggle*=-1;
		}
		if(mySpringies.getKey('V')){
			mySpringies.clearKey('V');
			viscToggle*=-1;
		}
		if(mySpringies.getKey('M')){
			mySpringies.clearKey('M');
			massToggle*=-1;
		}
		//bottom wall
		if(mySpringies.getKey('3')){
			mySpringies.clearKey('3');
			wallToggle[2]*=-1;
		}
		//left wall
		if(mySpringies.getKey('4')){
			mySpringies.clearKey('4');
			wallToggle[3]*=-1;
		}
		//top wall
		if(mySpringies.getKey('1')){
			mySpringies.clearKey('1');
			wallToggle[0]*=-1;
		}
		//right wall
		if(mySpringies.getKey('2')){
			mySpringies.clearKey('2');
			wallToggle[1]*=-1;
		}
		if(mySpringies.getKey('=')){
			muscleToggle+=.01;
		}
		if(mySpringies.getKey('-')){
			if(muscleToggle>0)muscleToggle-=.01;
		}
		
		
	}

	
	

}
