package springies;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JComponent;


public class GetForces extends JComponent {
	public static String[] grav;
	public static String[]centermass;
	public static String viscosity;
	public static ArrayList<String[]> walls;
	
	public void getEnvironment(){
		File file = new File("environment.xml");
		XML_Parser p = new XML_Parser(file);
		p.parse();
		grav = p.mygrav;
		centermass = p.mycentermass;
		viscosity = p.myviscosity;	
		walls = p.walls;
	}
	
	public double[] centerOfMass(HashMap<String,Mass> m){
		double totalx = 0;
		double totaly = 0;
		double totalMass = 0;
		for (String l: m.keySet()){
			totalMass += m.get(l).getMass();
			totalx += (m.get(l).x * m.get(l).getMass());
			totaly += (m.get(l).y * m.get(l).getMass());
		}
		double[] myCenter = {(totalx /(totalMass)), (totaly /(totalMass))};
		return myCenter;
	}
	
	
}
