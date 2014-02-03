package springies;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML_Parser {
	HashMap<String,Double[]> masses;
	ArrayList<String[]> springs;
	ArrayList<String[]> muscles;
	ArrayList<String[]> walls = new ArrayList<String[]>();
	String[] mygrav;
	String[] mycentermass;
	static String myviscosity;
	public void parse() {
		HashMap<String,Double[]> masslist = new HashMap<String,Double[]>();
		ArrayList<String[]> springlist = new ArrayList<String[]>();
		ArrayList<String[]> musclelist = new ArrayList<String[]>();
		
		ArrayList<String[]> walllist = new ArrayList<String[]>();
		String[] grav = new String[2];
		String[] centermass = new String[2];
	
		try {


			File file = new File("example.xml");
//			File file = new File("test.xml");
//			File file = new File("example.xml");
			File file2 = new File("environment.xml");

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(file);
			Document doc2 = dBuilder.parse(file2);
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			if (doc.hasChildNodes()) {

				printNote(doc.getChildNodes(),masslist,springlist,musclelist);

			}
			if (doc2.hasChildNodes()) {

				printNote2(doc2.getChildNodes(),walllist,grav,centermass);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//System.out.println(springlist.size());
//		for(String[] s:musclelist){
//			System.out.println(s[4]);
//		}
		muscles = musclelist;
		masses=masslist;
		springs=springlist;
		walls = walllist;
		
		mygrav=grav;
		mycentermass=centermass;
		
	


	}

	public static void printNote(NodeList nodeList, HashMap<String,Double[]> masslist,ArrayList<String[]> springlist, ArrayList<String[]> musclelist) {
		//System.out.println(nodeList.getLength());
		String name ="";
		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);
			//System.out.println(count + " / " + nodeList.getLength());

			// make sure it's element node.

			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// get node name and value


				if (tempNode.hasAttributes()) {

					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();

				
					String[] s = new String[4];
					String[] m = new String[5];
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						if(tempNode.getNodeName().equals("mass") || tempNode.getNodeName().equals("fixed")){
							//System.out.println(tempNode.getNodeName());
							if(node.getNodeName().equals("id")){
								name=node.getNodeValue();
								masslist.put(name, new Double[6]);
								
							}
							else if(node.getNodeName().equals("x")){
								Double[] temp = masslist.get(name);
								temp[0]=Double.parseDouble(node.getNodeValue());
								masslist.put(name, temp);
							}
							else if(node.getNodeName().equals("y")){
								Double[] temp = masslist.get(name);
								temp[1]=Double.parseDouble(node.getNodeValue());
								temp[2]=0.0; temp[3]=0.0; temp[4]=1.0;
								masslist.put(name, temp);
							}
							else if(node.getNodeName().equals("vx")){
								Double[] temp = masslist.get(name);
								temp[2]=Double.parseDouble(node.getNodeValue());
								masslist.put(name, temp);
							}
							else if(node.getNodeName().equals("vy")){
								Double[] temp = masslist.get(name);
								temp[3]=Double.parseDouble(node.getNodeValue());
								masslist.put(name, temp);
							}
							else if(node.getNodeName().equals("mass")){
								Double[] temp = masslist.get(name);
								temp[4]=Double.parseDouble(node.getNodeValue());
								masslist.put(name, temp);
							}
							if(tempNode.getNodeName().equals("fixed")){
								Double[] temp = masslist.get(name);
								temp[5]=7.0;
								masslist.put(name, temp);
							}
							
						}
						else if(tempNode.getNodeName().equals("spring")){
							s[i]=node.getNodeValue();
							
						}
						else if(tempNode.getNodeName().equals("muscle")){
							m[i]=node.getNodeValue();
							//System.out.println(i + " " + node.getNodeName() + " : " + node.getNodeValue());
							//System.out.println(node.getNodeName());
						}
						
						
					}

					if(s[3]==null)s[3]="1";
					if(m[4]==null)m[4]="1";
					if(s[2]!=null)springlist.add(s);
					if(m[2]!=null)musclelist.add(m);
				}


				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote(tempNode.getChildNodes(),masslist,springlist,musclelist);

				}

				//System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

			}

		}


	}
	public static void printNote2(NodeList nodeList, ArrayList<String[]> walllist, String[] grav, String[] centermass) {
		//System.out.println(nodeList.getLength());
		String name ="";
		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);
			//System.out.println(count + " / " + nodeList.getLength());

			// make sure it's element node.

			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// get node name and value


				if (tempNode.hasAttributes()) {

					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();

				
				
					String[] wall = new String[3];
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						if(tempNode.getNodeName().equals("gravity") ){							
							grav[i] = node.getNodeValue();
						}
						else if(tempNode.getNodeName().equals("viscosity") ){							
							 myviscosity = node.getNodeValue();
							 System.out.println(myviscosity);
						}
						else if(tempNode.getNodeName().equals("centermass") ){							
							centermass[i] = node.getNodeValue();
						}
						else if(tempNode.getNodeName().equals("wall") ){							
							wall[i] = node.getNodeValue();
						}
						
						
						
					}
					
					

//					if(s[3]==null)s[3]="1";
//					if(m[4]==null)m[4]="1";
//					if(s[2]!=null)springlist.add(s);
//					if(m[2]!=null)musclelist.add(m);
					if(wall[0]!=null)walllist.add(wall);
					
				}
				


				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote2(tempNode.getChildNodes(),walllist, grav, centermass);

				}

				//System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

			}

		}


	}


}