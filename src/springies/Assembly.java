package springies;

import java.util.ArrayList;
import java.util.HashMap;

import jboxGlue.PhysicalObject;

public class Assembly {
	public static HashMap<String,Mass> m = new HashMap<String,Mass>();
	//Need to find a way to not make this public static
	private PhysicalObject[] myWallArray;
	
	public Assembly(PhysicalObject[] wallarray){
		myWallArray = wallarray;
	}
	public void addAssembly(){
		XML_Parser p = new XML_Parser();
		p.parse();
		addMasses(p);
		addSprings(p);
		addMuscles(p);
	}
	
	private void addMasses (XML_Parser p)
	{		
		System.out.println("new");
		HashMap<String,Double[]> masseslist = p.masses;
		for(String mass:masseslist.keySet()){   			
			if(masseslist.get(mass)[5]!=null)				
				m.put(mass, new FixedMass(mass,masseslist.get(mass)[0],masseslist.get(mass)[1]));
			else
				m.put(mass, new Mass(mass,masseslist.get(mass)[0],masseslist.get(mass)[1],masseslist.get(mass)[2],masseslist.get(mass)[3],masseslist.get(mass)[4],myWallArray));	
		}
	}

	private void addSprings(XML_Parser p){

		ArrayList<String[]> springslist=p.springs;  
		for(String[] spring:springslist){  
			Mass m1 = m.get(spring[0]);
			Mass m2 = m.get(spring[1]);			
			PhysicalObject sp = new Spring(m1, m2, Double.parseDouble(spring[3]), Double.parseDouble(spring[2]));
		}
	}
	
	private void addMuscles(XML_Parser p){		
		ArrayList<String[]> muscleslist=p.muscles;
		for(String[] muscle: muscleslist){
			Mass m1 = m.get(muscle[0]);			
			Mass m2 = m.get(muscle[2]);
			PhysicalObject muscleObj = new Muscle(m1, m2, Double.parseDouble(muscle[3]), Double.parseDouble(muscle[4]), Double.parseDouble(muscle[1]));
		}
	}


}
