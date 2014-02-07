package springies;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import jboxGlue.PhysicalObject;

public class Assembly extends JComponent{
	public static HashMap<String,Mass> m = new HashMap<String,Mass>();
	//Need to find a way to not make this public static
	private PhysicalObject[] myWallArray;
	public static int assemblyNumber=100;
	
	public Assembly(PhysicalObject[] wallarray){
		myWallArray = wallarray;
	}
	
	public void addAssembly(){
		assemblyNumber++;
//		System.out.println(assemblyNumber);
		XML_Parser p = new XML_Parser(getNewFile());
		p.parse();
		addMasses(p);
		addSprings(p);
		addMuscles(p);
	}
	
	private void addMasses (XML_Parser p)
	{		
		Map<String,Double[]> masseslist = p.masses;
		int c = 0;
		for(String mass:masseslist.keySet()){   
			System.out.println(m.size());
			if(masseslist.get(mass)[5]!=null)				
				m.put(mass + assemblyNumber, new FixedMass(mass+assemblyNumber,masseslist.get(mass)[0],masseslist.get(mass)[1]));
			else
				m.put(mass+assemblyNumber, new Mass(mass+assemblyNumber,masseslist.get(mass)[0],masseslist.get(mass)[1],masseslist.get(mass)[2],masseslist.get(mass)[3],masseslist.get(mass)[4],myWallArray));		
		}	
	}

	private void addSprings(XML_Parser p){
		Collection<String[]> springslist=p.springs;  
		for(String[] spring:springslist){  
			Mass m1 = m.get(spring[0]+assemblyNumber);
			Mass m2 = m.get(spring[1]+assemblyNumber);			
			PhysicalObject sp = new Spring(m1, m2, Double.parseDouble(spring[3]), Double.parseDouble(spring[2]));
		}
	}
	
	private void addMuscles(XML_Parser p){		
		Collection<String[]> muscleslist=p.muscles;
		for(String[] muscle: muscleslist){
			Mass m1 = m.get(muscle[0]);			
			Mass m2 = m.get(muscle[2]);
			PhysicalObject muscleObj = new Muscle(m1, m2, Double.parseDouble(muscle[3]), Double.parseDouble(muscle[4]), Double.parseDouble(muscle[1]));
		}
	}
	public File getNewFile() {
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "XML Files", "xml");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(getParent());
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	    }
	    return chooser.getSelectedFile();	
	}
}
