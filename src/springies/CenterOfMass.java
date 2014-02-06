package springies;

import jboxGlue.PhysicalObject;

public class CenterOfMass {
	public double[] getCenterOfMass(){
		double totalx = 0;
		double totaly = 0;
		double totalMass = 0;
		for (String l: Assembly.m.keySet()){
			totalMass += Assembly.m.get(l).getMass();
			totalx += (Assembly.m.get(l).x * Assembly.m.get(l).getMass());
			totaly += (Assembly.m.get(l).y * Assembly.m.get(l).getMass());
		}
		double[] myCenter = {(totalx /(totalMass)), (totaly /(totalMass))};
		return myCenter;
	}

	public void doCenterOfMass(double xpos, double ypos,PhysicalObject mass){
		if(ToggleForces.massToggle==1){
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
