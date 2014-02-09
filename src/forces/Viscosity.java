package forces;
import jboxGlue.PhysicalObject;

public class Viscosity extends GlobalForce {
	private String viscosity;
	public Viscosity(String readInViscosity){
		viscosity = readInViscosity;
	}
	public void doForce(PhysicalObject mass){
		if (getToggle()) {
			double VISCOSITY = Double.parseDouble(viscosity);
			double xvel = mass.xspeed;
			double yvel = mass.yspeed;
			
			if (xvel > 0) {
				mass.setForce(-VISCOSITY * xvel, 0);
			}
			
			if (xvel < 0) {
				mass.setForce(-VISCOSITY * xvel, 0);
			}
			
			if (yvel < 0) {
				mass.setForce(0, -VISCOSITY * yvel);
			}
			
			if (yvel > 0) {
				mass.setForce(0, -VISCOSITY * yvel);
			}
			
		} else {
			mass.setForce(0,0);
		}
		
	}
}
