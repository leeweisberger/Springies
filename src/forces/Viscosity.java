package forces;

import springies.GetForces;
import jboxGlue.PhysicalObject;

public class Viscosity extends GlobalForce {
	public void doForce(double xvel, double yvel,PhysicalObject mass){
		double VISCOSITY = Double.parseDouble(GetForces.viscosity);
		
		if(ToggleForces.viscToggle==1){
			if(xvel>0) mass.setForce(-VISCOSITY,0);
			if(xvel<0) mass.setForce(VISCOSITY,0);
			if(yvel>0) mass.setForce(0,-VISCOSITY);
			if(yvel<0) mass.setForce(0,VISCOSITY);
		}
		
	}
}
