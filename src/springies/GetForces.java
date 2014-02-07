package springies;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;


public class GetForces extends JComponent {
	public static String[] grav;
	public static String[]centermass;
	public static String viscosity;
	public static List<String[]> walls;
	
	public void getEnvironment(){
		File file = new File("environment.xml");
		XML_Parser p = new XML_Parser(file);
		p.parse();
		grav = p.mygrav;
		centermass = p.mycentermass;
		viscosity = p.myviscosity;	
		walls = p.walls;
	}
	
	
	
	
}
