package cz.ucl.javase.xmljsonparsing;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

public class DomRiversMain {

    private static List<River> rivers = new ArrayList<>();

    public static void main(String[] arg) {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(DomRiversMain.class.getResourceAsStream("/world.xml"));

            doc.getDocumentElement().normalize();

            Element riversElement = (Element) doc.getElementsByTagName("rivers").item(0);
            NodeList nList = riversElement.getChildNodes();
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String length = eElement.getAttribute("length");
                    rivers.add(new River(eElement.getAttribute("riverName"), (length != null && length.length() > 0) ? new Long(length) : 0L));

                }
            }


            printResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void printResult() {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("rivers");
            doc.appendChild(rootElement);

            Collections.sort(rivers, new Comparator<River>() {
                @Override
                public int compare(River o1, River o2) {
                    return - o1.length.compareTo(o2.length);
                }
            });
            for (int i = 0; i < 10&& i < rivers.size(); i++) {
                createNode(doc, rootElement, rivers.get(i));
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("rivers.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private static void createNode(Document doc, Element rootElement, River river) {
        // staff elements
        Element eRiver = doc.createElement("river");
        rootElement.appendChild(eRiver);

        // set attribute to staff element
        Attr riverName = doc.createAttribute("riverName");
        riverName.setValue(river.name);
        eRiver.setAttributeNode(riverName);

        Attr length = doc.createAttribute("length");
        length.setValue(river.length.toString());
        eRiver.setAttributeNode(length);

    }


    private static class River {
        String name;
        Long length;

        public River(String name, Long length) {
            this.name = name;
            this.length = length;
        }
    }
}
