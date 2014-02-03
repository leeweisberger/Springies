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
	public void parse() {
		HashMap<String,Double[]> masslist = new HashMap<String,Double[]>();
		ArrayList<String[]> springlist = new ArrayList<String[]>();
		ArrayList<String[]> musclelist = new ArrayList<String[]>();
		try {


			File file = new File("ball.xml");
//			File file = new File("test.xml");
//			File file = new File("example.xml");


			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			if (doc.hasChildNodes()) {

				printNote(doc.getChildNodes(),masslist,springlist,musclelist);

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
								masslist.put(name, new Double[5]);
								
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


}