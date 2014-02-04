package springies;

public class CenterOfMass {
	public double[] getCenterOfMass(){
		double totalx = 0;
		double totaly = 0;
		double totalMass = 0;
		for (String l: Springies.m.keySet()){
			totalMass += Springies.m.get(l).getMass();
			totalx += (Springies.m.get(l).x * Springies.m.get(l).getMass());
			totaly += (Springies.m.get(l).y * Springies.m.get(l).getMass());
		}
		double[] myCenter = {(totalx /(totalMass)), (totaly /(totalMass))};
		return myCenter;
	}
	
	public double[] centerOfMass(double xpos, double ypos){
		double[] center = getCenterOfMass();
		double centerX = center[0];
		double centerY = center[1];
		double xDist = xpos - centerX;
		double yDist = ypos - centerY;
		//System.out.println("asdf " + s.centermass[1]);
		double xForce = Integer.parseInt(Forces.centermass[1])/(Math.pow(xDist, Double.parseDouble(Forces.centermass[0])));
		double yForce = Integer.parseInt(Forces.centermass[1])/(Math.pow(yDist, Double.parseDouble(Forces.centermass[0])));
		if (Math.abs(xDist) < 10){
			xForce = 0;
		}
		if (Math.abs(yDist) < 10){
			yForce = 0;
		}
		//		System.out.println(xForce + " " + yForce);
		if(xDist > 0) return new double[] {-xForce,0};
		if(xDist < 0) return new double[] {xForce,0};
		if(yDist > 0) return new double[] {0,-yForce};
		if(yDist < 0) return new double[] {0,yForce};
		return new double[] {0,0};
			
		

	}
}
