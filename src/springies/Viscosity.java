package springies;

import org.jbox2d.common.Vec2;

public class Viscosity {
	public double[] viscosity(double xvel, double yvel){
		double VISCOSITY = Double.parseDouble(Forces.viscosity);
		
		if(xvel>0) return new double[]{-VISCOSITY,0};
		if(xvel<0) return new double[]{VISCOSITY,0};
		if(yvel>0) return new double[]{0,-VISCOSITY};
		if(yvel<0) return new double[]{0,VISCOSITY};
		return new double[] {0,0};
	}
}
