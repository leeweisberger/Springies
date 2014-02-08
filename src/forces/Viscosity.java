package forces;

import springies.GetForces;
import jboxGlue.PhysicalObject;

public class Viscosity extends GlobalForce {
	public void doForce(PhysicalObject mass){
		double VISCOSITY = Double.parseDouble(GetForces.viscosity);
		double xvel = mass.xspeed;
		double yvel = mass.yspeed;
			if(xvel>0) { mass.setForce(-VISCOSITY * xvel,0);}
			if(xvel<0) { mass.setForce(-VISCOSITY * xvel,0);}
			if(yvel<0) { mass.setForce(0,-VISCOSITY * yvel);}
			if(yvel>0) { mass.setForce(0,-VISCOSITY * yvel);}
		
	}
}
