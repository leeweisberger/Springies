package forces;

import java.util.List;

import Objects.Mass;
import springies.Factory;
import jboxGlue.PhysicalObject;

public class CenterOfMass extends GlobalForce {
	private String[] myCenterOfMass;

	public CenterOfMass(String[] readInCenterOfMass){
		myCenterOfMass = readInCenterOfMass;
	}

	private double[] getCenterOfMass(List<Mass> masses){
		double totalx = 0;
		double totaly = 0;
		double totalMass = 0;
		for (Mass mass: masses){
//			System.out.println(mass.x);
			totalMass += mass.getMass();
			totalx += mass.x * mass.getMass();
			totaly += mass.y * mass.getMass();
		}
		double[] myCenter = {(totalx /(totalMass)), (totaly /(totalMass))};
		return myCenter;
	}

	public void doForce(PhysicalObject mass, Factory myAssembly){
		if (getToggle()) {
			double[] center = getCenterOfMass(myAssembly.getMassList());
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

	@Override
	public void doForce(PhysicalObject mass) {
		mass.setForce(0,0);
	}

}
