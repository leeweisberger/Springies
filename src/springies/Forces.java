package springies;

import java.util.ArrayList;
import java.util.HashMap;

public class Forces {
	static String[] grav;
	static String[] centermass;
	static String viscosity;
	static ArrayList<String[]> walls = new ArrayList<String[]>();
	public void getEnvironment(){
		XML_Parser p = new XML_Parser();
		p.parse();
		ArrayList<String[]> walllist = p.walls;
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
