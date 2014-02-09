package forces;

import jboxGlue.PhysicalObject;

abstract public class GlobalForce{
	private boolean toggle=true;
	
	protected void changeToggle(){
		toggle = !toggle;
	}
	
	protected boolean getToggle(){
		return toggle;
	}

	public abstract void doForce(PhysicalObject Mass);
}
