//package forces;
//
//import java.util.List;
//
//import org.jbox2d.common.Vec2;
//
//import springies.Factory;
//import springies.Springies;
//import springies.Walls;
//import Objects.Mass;
//
//public class DoForces {
//
//	private Factory myAssembly;
//	private Springies mySpringy;
//
//	public DoForces(Springies springy, Factory assembly){
//
//		mySpringy = springy;
//
//		myAssembly = assembly;
//	}
//
//	private void doGravity(Mass mass) {
//		Gravity grav = new Gravity();
//		if(mySpringy.getGravToggle())grav.doForce(mass);
//	}	
//
//	private void doViscosity(Mass mass) {
//		Viscosity v = new Viscosity();
//		Vec2 currentVector = mass.getVec();
//		if(mySpringy.getViscToggle())v.doForce(mass);
//	}
//
//	private void doWallRepulsion(Mass mass) {
//
//		WallRepulsion wr = new WallRepulsion(Walls.wallarray,mass,Environment.walls);
//		for(int i=0; i<4;i++){
//			if(mySpringy.getWallToggles()[i])
//				wr.doForce(mass, i);
//		}
//	}
//
//	private void doCenterOfMass(Mass mass, List<Mass> masses) {
//		CenterOfMass c = new CenterOfMass(masses);
//		if(mySpringy.getMassToggle())c.doForce(mass);
//	}
//
//	public void doForces(){	
//
//		List<Mass> Masses = myAssembly.getMassList();
//		for(Mass mass:Masses){
//			doGravity(mass);
//			doViscosity(mass);
//			doWallRepulsion(mass);
//			doCenterOfMass(mass, Masses);
//		}
//	}
//
//}
