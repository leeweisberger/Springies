package forces;

import springies.Assembly;
import springies.GetForces;
import jboxGlue.PhysicalObject;

public class CenterOfMass extends GlobalForce {
	private double[] getCenterOfMass(){
		double totalx = 0;
		double totaly = 0;
		double totalMass = 0;
		for (String l: Assembly.getMap().keySet()){
			totalMass += Assembly.getMap().get(l).getMass();
			totalx += (Assembly.getMap().get(l).x * Assembly.getMap().get(l).getMass());
			totaly += (Assembly.getMap().get(l).y * Assembly.getMap().get(l).getMass());
		}
		double[] myCenter = {(totalx /(totalMass)), (totaly /(totalMass))};
		System.out.println(myCenter[0]);
		return myCenter;
	}

	@Override
	public void doForce(double xpos, double ypos, PhysicalObject mass){
		if(ToggleForces.getMassToggle()==true){
			double[] center = getCenterOfMass();
			double centerX = center[0];
			double centerY = center[1];
			double xDist = xpos - centerX;
			double yDist = ypos - centerY;
			//System.out.println("asdf " + s.centermass[1]);
			double xForce = Integer.parseInt(GetForces.centermass[1])/(Math.pow(xDist, Double.parseDouble(GetForces.centermass[0])));
			double yForce = Integer.parseInt(GetForces.centermass[1])/(Math.pow(yDist, Double.parseDouble(GetForces.centermass[0])));
			
			if (Math.abs(xDist) < 10){
				xForce = 0;
			}
			
			if (Math.abs(yDist) < 10){
				yForce = 0;
			}
			
			//		System.out.println(xForce + " " + yForce);
			if(xDist > 0) mass.setForce(-xForce,0);
			if(xDist < 0) mass.setForce(xForce,0);
			if(yDist > 0) mass.setForce(0,-yForce);
			if(yDist < 0) mass.setForce(0,yForce);
		}
	}
}
