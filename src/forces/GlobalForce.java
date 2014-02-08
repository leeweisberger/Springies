package forces;

import jboxGlue.PhysicalObject;

abstract public class GlobalForce{
	protected int toggle;
	
	protected void setToggle(int newToggle){
		toggle = newToggle;
	}
	public abstract void doForce(PhysicalObject Mass);
}
