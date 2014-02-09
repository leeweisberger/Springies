package forces;

import java.util.List;

import Objects.Mass;
import springies.Factory;
import jboxGlue.PhysicalObject;

public class CenterOfMass extends GlobalForce {
	private List<Mass> myMasses;
	private String[] myCenterOfMass;
	
	public CenterOfMass(String[] readInCenterOfMass){
		myCenterOfMass = readInCenterOfMass;
	}
	
	
	
	public CenterOfMass(List<Mass> masses) {
		myMasses=masses;
	}

	private double[] getCenterOfMass(){
		double totalx = 0;
		double totaly = 0;
		double totalMass = 0;
		for (Mass mass: myMasses){
			totalMass += mass.getMass();
			totalx += mass.x * mass.getMass();
			totaly += mass.y * mass.getMass();
		}
		double[] myCenter = {(totalx /(totalMass)), (totaly /(totalMass))};
		System.out.println(myCenter[1]);
		return myCenter;
	}

	@Override

	public void doForce(PhysicalObject mass){
			if (getToggle()) {
				double[] center = getCenterOfMass();
				double centerX = center[0];
				double centerY = center[1];
				double xDist = mass.x - centerX;
				double yDist = mass.y - centerY;
				double xForce = Integer.parseInt(myCenterOfMass[1])
						/ (Math.pow(xDist,
								Double.parseDouble(myCenterOfMass[0])));
				double yForce = Integer.parseInt(myCenterOfMass[1])
						/ (Math.pow(yDist,
								Double.parseDouble(myCenterOfMass[0])));
				if (Math.abs(xDist) < 10) {
					xForce = 0;
				}
				if (Math.abs(yDist) < 10) {
					yForce = 0;
				}
				if (xDist > 0)
					mass.setForce(-xForce, 0);
				if (xDist < 0)
					mass.setForce(xForce, 0);
				if (yDist > 0)
					mass.setForce(0, -yForce);
				if (yDist < 0)
					mass.setForce(0, yForce);
			} else {
				mass.setForce(0, 0);
			}
		
	}
}
