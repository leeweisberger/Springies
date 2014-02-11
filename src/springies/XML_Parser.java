package springies;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML_Parser {
	public static final String SPRINGINESS = "1";
	private static final String YVEL = "0.0";
	private static final String XVEL = "0.0";
	private static final double FIXED = 7.0;
	public static final String MASS = "1.0";
	private Map<String,Double[]> masses = new HashMap<String,Double[]>();
	private Collection<String[]> springs= new ArrayList<String[]>();
	private Collection<String[]> muscles= new ArrayList<String[]>();
	private List<String[]> myWallForces = new ArrayList<String[]>();
	private String[] mygrav= new String[2];
	private String[] mycentermass= new String[2];
	private String myviscosity;
	public static final String[] DEFAULT_GRAV = {"20","90"};
	public static final String[] DEFAULT_CENTERMASS = {"50","2.0"};
	public static final String DEFAULT_VISCOSITY = "0.8";

	private File myFile;

	public XML_Parser(File f){
		myFile=f;
	}

	public void parse() {

		try {
			//File file = myFile;
			//			File file = new File("test.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			System.out.println(myFile.getName());
			Document doc = dBuilder.parse(myFile);
			if (doc.hasChildNodes()) {
				getInfo(doc.getChildNodes());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void getInfo(NodeList nodeList) {
		String name ="";
		String[] massAttributes = {"x","y","vx","vy","mass"};
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				if (tempNode.hasAttributes()) {

					NamedNodeMap nodeMap = tempNode.getAttributes();		
					String[] s = new String[4];
					String[] m = new String[5];
					String[] wall = new String[3];
					for (int i = 0; i < nodeMap.getLength(); i++) {

						Node node = nodeMap.item(i);
						if(tempNode.getNodeName().equals("mass") || tempNode.getNodeName().equals("fixed")){

							name = storeMassInfo(name, massAttributes,
									tempNode, node);
						}
						storeSpringInfo(tempNode, s, m, i, node);

						storeForcesInfo(tempNode, wall, i, node);

					}
					if(wall[0]!=null)myWallForces.add(wall);
					if(s[3]==null)s[3]=SPRINGINESS;
					if(m[3]==null)m[3]=SPRINGINESS;
					if(s[2]!=null)springs.add(s);
					if(m[2]!=null)muscles.add(m);

				}
				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					getInfo(tempNode.getChildNodes());

				}
			}	
		}
	}

	public void putMassAttribute(String name, int position, String value){
		Double[] temp = masses.get(name);
		temp[position] = Double.parseDouble(value);
		masses.put(name, temp);
	}

	private void storeForcesInfo(Node tempNode, String[] wall, int i, Node node) {
		if(tempNode.getNodeName().equals("gravity") )						
			mygrav[i] = node.getNodeValue();
	
		else if(tempNode.getNodeName().equals("viscosity") )							
			myviscosity = node.getNodeValue();
	
		else if(tempNode.getNodeName().equals("centermass") )							
			mycentermass[i] = node.getNodeValue();
	
		else if(tempNode.getNodeName().equals("wall") )
			wall[i] = node.getNodeValue();
	}

	private void storeSpringInfo(Node tempNode, String[] s, String[] m, int i,
			Node node) {
		String[] springAttributes = {"a","b","restlength","constant"};

		if(tempNode.getNodeName().equals("spring")){
			for(int u=0;u<springAttributes.length;u++){
				if(node.getNodeName().equals(springAttributes[u])){
					s[u]=node.getNodeValue();
				}
			}
		}

		String[] muscleAttributes = {"a","b","restlength","constant","amplitude"};
		if(tempNode.getNodeName().equals("muscle")){
			for(int u=0;u<muscleAttributes.length;u++){
				if(node.getNodeName().equals(muscleAttributes[u])){
					m[u]=node.getNodeValue();
				}
			}
		}


	}

	private String storeMassInfo(String name, String[] massAttributes,
			Node tempNode, Node node) {
		if(node.getNodeName().equals("id")){
			name=node.getNodeValue();
			masses.put(name, new Double[6]);

		}
		for(int j=0;j<massAttributes.length;j++){
			if(node.getNodeName().equals(massAttributes[j]))
				putMassAttribute(name,j,node.getNodeValue());
		}

		if(tempNode.getNodeName().equals("fixed")){
			Double[] temp = masses.get(name);
			temp[5]=FIXED;
			masses.put(name, temp);
		}	
		if(masses.get(name)[2]==null){
			putMassAttribute(name,2,XVEL);
			putMassAttribute(name,3,YVEL);
		}
		if(masses.get(name)[4]==null)
			putMassAttribute(name,4,MASS);
		return name;
	}

	public Map<String, Double[]> getMasses() {
		return masses;
	}

	public Collection<String[]> getSprings() {
		return springs;
	}

	public Collection<String[]> getMuscles() {
		return muscles;
	}

	public String[] getGravity() {
		if(mygrav==null){
			return DEFAULT_GRAV;
	
		}
		return mygrav;
	}

	public String[] getCenterMass() {
		if(mycentermass==null)return DEFAULT_CENTERMASS;
		return mycentermass;
	}

	public String getViscosity() {
		if(myviscosity==null)return DEFAULT_VISCOSITY;
		return myviscosity;
	}

	public List<String[]> getWalls(){
		if(myWallForces==null){
			List<String[]> wf = new ArrayList<String[]>();
			for(int u=0;u<4;u++)
				wf.add(new String[]{"40","1.5"});
			return wf;
		}
		return myWallForces;
	}
}
