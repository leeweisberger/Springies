package forces;

import java.util.List;

import org.jbox2d.common.Vec2;

import springies.Assembly;
import springies.GetForces;
import springies.Springies;
import springies.Walls;
import Objects.Mass;

public class DoForces {
	private Springies mySpringies;
	private Assembly myAssembly;

	public DoForces(Springies springies, Assembly assembly){
		mySpringies = springies;
		myAssembly = assembly;
	}

	private void doGravity(Mass mass) {
		Gravity grav = new Gravity();
		if(mySpringies.gravToggle)grav.doForce(mass);
	}	

	private void doViscosity(Mass mass) {
		Viscosity v = new Viscosity();
		Vec2 currentVector = mass.getVec();
		if(mySpringies.viscToggle)v.doForce(mass);
	}

	private void doWallRepulsion(Mass mass) {
		WallRepulsion wr = new WallRepulsion(Walls.wallarray,mass,GetForces.walls);
		wr.doForce(mass);
	}

	private void doCenterOfMass(Mass mass) {
		CenterOfMass c = new CenterOfMass();
		if(mySpringies.massToggle)c.doForce(mass);
	}

	public void doForces(){	
		List<Mass> myMasses = myAssembly.getMassList();
		for(Mass mass:myMasses){
			doGravity(mass);
			doViscosity(mass);
			doWallRepulsion(mass);
			doCenterOfMass(mass);
		}
	}

}
