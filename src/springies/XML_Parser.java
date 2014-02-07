package springies;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
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
	Map<String,Double[]> masses = new HashMap<String,Double[]>();
	Collection<String[]> springs= new ArrayList<String[]>();
	Collection<String[]> muscles= new ArrayList<String[]>();
	List<String[]> walls = new ArrayList<String[]>();
	String[] mygrav= new String[2];
	String[] mycentermass= new String[2];
	static String myviscosity;
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
				printNote(doc.getChildNodes());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void printNote(NodeList nodeList) {
		String name ="";
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

							if(node.getNodeName().equals("id")){
								name=node.getNodeValue();
								masses.put(name, new Double[6]);

							}
							else if(node.getNodeName().equals("x")){
								Double[] temp = masses.get(name);
								temp[0]=Double.parseDouble(node.getNodeValue());
								masses.put(name, temp);
							}
							else if(node.getNodeName().equals("y")){
								Double[] temp = masses.get(name);
								temp[1]=Double.parseDouble(node.getNodeValue());
								temp[2]=0.0; temp[3]=0.0; temp[4]=1.0;
								masses.put(name, temp);
							}
							else if(node.getNodeName().equals("vx")){
								Double[] temp = masses.get(name);
								temp[2]=Double.parseDouble(node.getNodeValue());
								masses.put(name, temp);
							}
							else if(node.getNodeName().equals("vy")){
								Double[] temp = masses.get(name);
								temp[3]=Double.parseDouble(node.getNodeValue());
								masses.put(name, temp);
							}
							else if(node.getNodeName().equals("mass")){
								Double[] temp = masses.get(name);
								temp[4]=Double.parseDouble(node.getNodeValue());
								masses.put(name, temp);
							}
							if(tempNode.getNodeName().equals("fixed")){
								Double[] temp = masses.get(name);
								temp[5]=7.0;
								masses.put(name, temp);
							}			
						}
						else if(tempNode.getNodeName().equals("spring")){
							s[i]=node.getNodeValue();

						}
						else if(tempNode.getNodeName().equals("muscle")){
							m[i]=node.getNodeValue();
						}
						if(tempNode.getNodeName().equals("gravity") ){							
							mygrav[i] = node.getNodeValue();
						}
						else if(tempNode.getNodeName().equals("viscosity") ){							
							myviscosity = node.getNodeValue();
						}
						else if(tempNode.getNodeName().equals("centermass") ){							
							mycentermass[i] = node.getNodeValue();
						}
						else if(tempNode.getNodeName().equals("wall") ){
							wall[i] = node.getNodeValue();
						}
					}
					if(wall[0]!=null)walls.add(wall);
					if(s[3]==null)s[3]="1";
					if(m[4]==null)m[4]="1";
					if(s[2]!=null)springs.add(s);
					if(m[2]!=null)muscles.add(m);
				}
				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote(tempNode.getChildNodes());
					
				}
			}	
		}
	}
}
