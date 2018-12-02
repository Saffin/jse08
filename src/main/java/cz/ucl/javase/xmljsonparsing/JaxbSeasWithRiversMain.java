package cz.ucl.javase.xmljsonparsing;

import cz.ucl.javase.xmljsonparsing.world.RiverType;
import cz.ucl.javase.xmljsonparsing.world.SeaType;
import cz.ucl.javase.xmljsonparsing.world.ToType;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JaxbSeasWithRiversMain {

    private static Map<String, Sea> seaById = new HashMap<>();

    public static void main(String[] arg) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WorldType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement element = (JAXBElement) jaxbUnmarshaller.unmarshal(JaxbSeasWithRiversMain.class.getResourceAsStream("/world.xml"));
            WorldType worldType = (WorldType) element.getValue();


            for (SeaType seaType : worldType.getSeas().getSea()) {
                seaById.put(seaType.getId(), new Sea(seaType.getSeaName()));
            }

            for (RiverType riverType : worldType.getRivers().getRiver()) {
                List<ToType> to = riverType.getTo();
                if (to == null || to.size() == 0) {
                    continue;
                }
                ToType toType = to.get(0);
                if ("sea".equals(toType.getType()) && seaById.containsKey(toType.getWater())) {
                    seaById.get(toType.getWater()).rivers.add(riverType.getRiverName());
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
            Element rootElement = doc.createElement("seas");
            doc.appendChild(rootElement);

            for (Sea sea : seaById.values()) {
                if (sea.rivers.size() < 8) {
                    continue;
                }
                createNode(doc, rootElement, sea);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("seasWithRivers.xml"));

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

    private static void createNode(Document doc, Element rootElement, Sea sea) {
        // staff elements
        Element eSea = doc.createElement("sea");
        rootElement.appendChild(eSea);

        // set attribute to staff element
        Attr seaName = doc.createAttribute("seaName");
        seaName.setValue(sea.name);
        eSea.setAttributeNode(seaName);

        for (String riverName : sea.rivers) {
            Element eRiverName = doc.createElement("riverName");
            eRiverName.setTextContent(riverName);
            eSea.appendChild(eRiverName);
        }

    }


    private static class Sea {
        String name;
        List<String> rivers = new ArrayList<>();

        public Sea(String name) {
            this.name = name;
        }
    }
}
