package cz.ucl.javase.xmljsonparsing;

import cz.ucl.javase.xmljsonparsing.world.IslandType;
import cz.ucl.javase.xmljsonparsing.world.WorldType;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JaxbIslandsMain {

    private static List<Island> islands = new ArrayList<>();

    public static void main(String[] arg) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WorldType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement element = (JAXBElement) jaxbUnmarshaller.unmarshal(JaxbIslandsMain.class.getResourceAsStream("/world.xml"));
            WorldType worldType = (WorldType) element.getValue();


            for (IslandType islandType : worldType.getIslands().getIsland()) {
                islands.add(new Island(islandType.getIslandName(), (islandType.getArea() != null) ? islandType.getArea(): 0f));
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
            Element rootElement = doc.createElement("islands");
            doc.appendChild(rootElement);

            Collections.sort(islands, new Comparator<Island>() {
                @Override
                public int compare(Island o1, Island o2) {
                    return -o1.area.compareTo(o2.area);
                }
            });
            for (int i = 0; i < 5 && i < islands.size(); i++) {
                createNode(doc, rootElement, islands.get(i));
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("islands.xml"));

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

    private static void createNode(Document doc, Element rootElement, Island island) {
        // staff elements
        Element eRiver = doc.createElement("island");
        rootElement.appendChild(eRiver);

        // set attribute to staff element
        Attr riverName = doc.createAttribute("islandName");
        riverName.setValue(island.name);
        eRiver.setAttributeNode(riverName);

        Attr length = doc.createAttribute("area");
        length.setValue(island.area.toString());
        eRiver.setAttributeNode(length);

    }


    private static class Island {
        String name;
        Float area;

        public Island(String name, Float area) {
            this.name = name;
            this.area = area;
        }
    }
}
