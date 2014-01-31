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
	ArrayList<String[]> masses;
	ArrayList<String[]> springs;
	public void parse() {
		ArrayList<String[]> masslist = new ArrayList<String[]>();
		ArrayList<String[]> springlist = new ArrayList<String[]>();
		try {

			File file = new File("ball.xml");

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			if (doc.hasChildNodes()) {

				printNote(doc.getChildNodes(),masslist,springlist);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		masses=masslist;
		springs=springlist;
		

	}

	public static void printNote(NodeList nodeList, ArrayList<String[]> masslist,ArrayList<String[]> springlist) {
		//System.out.println(nodeList.getLength());
		for (int count = 0; count < nodeList.getLength(); count++) {
		
			Node tempNode = nodeList.item(count);
			//System.out.println(count + " / " + nodeList.getLength());
			
			// make sure it's element node.
			
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// get node name and value
				//System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				
				//System.out.println("Node Value =" + tempNode.getTextContent());

				if (tempNode.hasAttributes()) {
					
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
					
					String[] m = new String[3];
					String[] s = new String[4];
					for (int i = 0; i < nodeMap.getLength(); i++) {
						
						Node node = nodeMap.item(i);
					//	System.out.println("attr name : " + node.getNodeName());
						//System.out.println("attr value : " + node.getNodeValue());
						
						if(tempNode.getNodeName().equals("mass")){
							m[i]=node.getNodeValue();
						}
						else if(tempNode.getNodeName().equals("spring")){
							s[i]=node.getNodeValue();
						}

					}
					
					if(m[0]!=null)masslist.add(m);
					if(s[0]!=null)springlist.add(s);

				}
				
				
				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote(tempNode.getChildNodes(),masslist,springlist);

				}

				//System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
				
			}

		}
		

	}
	

}