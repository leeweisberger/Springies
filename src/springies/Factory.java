package springies;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import jboxGlue.PhysicalObject;
import jgame.JGColor;
import Objects.FixedMass;
import Objects.Mass;
import Objects.Muscle;
import Objects.Spring;

public class Factory extends JComponent{
	private Map<String,Mass> m = new HashMap<String,Mass>();
	private PhysicalObject[] myWallArray;
	private static int assemblyNumber=100;
	private List<Mass> myMassList = new ArrayList<Mass>();
	private JGColor[] myColors = new JGColor[]{JGColor.yellow,JGColor.red,JGColor.pink,JGColor.green,JGColor.cyan}; 
	
	public Factory(PhysicalObject[] wallarray){
		myWallArray = wallarray;
	}
	
	protected void addAssembly(){
		assemblyNumber++;
		XML_Parser p = new XML_Parser(new File("ball.xml"));
		p.parse();
		addMasses(p);
		addSprings(p);
		addMuscles(p);
	}
	
	private void addMasses (XML_Parser p)
	{		
		JGColor color = myColors[new Random().nextInt(myColors.length)];
		Map<String,Double[]> masseslist = p.masses;
		int c = 0;
		for(String mass:masseslist.keySet()){   
			if(masseslist.get(mass)[5]!=null)				
				m.put(mass + assemblyNumber, new FixedMass(mass+assemblyNumber,masseslist.get(mass)[0],masseslist.get(mass)[1]));
			else{
				Mass newmass = new Mass(mass+assemblyNumber,masseslist.get(mass)[0],masseslist.get(mass)[1],masseslist.get(mass)[2],masseslist.get(mass)[3],masseslist.get(mass)[4],color);
				m.put(mass+assemblyNumber, newmass);
				myMassList.add(newmass);				
			}
		}
		
	}
	public List<Mass> getMassList(){
		return myMassList;
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
			Mass m1 = m.get(muscle[0]+assemblyNumber);			
			Mass m2 = m.get(muscle[2]+assemblyNumber);
			System.out.println(m2);
			new Muscle(m1, m2, Double.parseDouble(muscle[3]), Double.parseDouble(muscle[4]), Double.parseDouble(muscle[1]));
		}
	}
	
	private File getNewFile() {
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
