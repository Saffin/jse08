package cz.ucl.javase.xmljsonparsing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DomCountriesMain {

    private static Map<String, String> countriesById = new HashMap<>();
    private static Set<String> countriesInOrganization = new HashSet<>();


    public static void main(String[] arg) {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(DomCountriesMain.class.getResourceAsStream("/world.xml"));

            doc.getDocumentElement().normalize();

            Element countriesElement = (Element) doc.getElementsByTagName("countries").item(0);
            NodeList countyNodes = countriesElement.getChildNodes();
            for (int temp = 0; temp < countyNodes.getLength(); temp++) {

                Node countyNode = countyNodes.item(temp);
                if (countyNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element countyElement = (Element) countyNode;
                    String countryName = countyElement.getAttribute("countryName");
                    String countryId = countyElement.getAttribute("id");
                    countriesById.put(countryId, countryName);
                }
            }

            Element organizationsElement = (Element) doc.getElementsByTagName("organizations").item(0);
            NodeList organizationNodes = organizationsElement.getChildNodes();
            for (int temp = 0; temp < organizationNodes.getLength(); temp++) {

                Node organizationNode = organizationNodes.item(temp);
                if (organizationNode.getNodeType() == Node.ELEMENT_NODE && "Food and Agriculture Organization".equals(((Element) organizationNode).getAttribute("organizationName"))) {

                    Element organizationElement = (Element) organizationNode;
                    NodeList countryNodes = organizationElement.getChildNodes();
                    for (int temp1 = 0; temp1 < countryNodes.getLength(); temp1++) {

                        Node countryNode = countryNodes.item(temp1);
                        if (countryNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element countyElement = (Element) countryNode;
                            String countryId = countyElement.getAttribute("country");
                            countriesInOrganization.add(countriesById.get(countryId));
                        }
                    }
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
            Element rootElement = doc.createElement("countries");
            doc.appendChild(rootElement);


            for (String country : countriesInOrganization) {
                createNode(doc, rootElement, country);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("countries.xml"));

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private static void createNode(Document doc, Element rootElement, String countryName) {
        // staff elements
        Element eCountry = doc.createElement("country");
        eCountry.setTextContent(countryName);
        rootElement.appendChild(eCountry);

    }

}
