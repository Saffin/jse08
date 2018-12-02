package cz.ucl.javase.xmljsonparsing;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SaxMountainsMain {

    private static List<Mountain> mountains = new ArrayList<>();

    public static void main(String[] arg) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("MOUNTAIN")) {
                        mountains.add(new Mountain(
                                attributes.getValue("mountainName"),
                                attributes.getValue("height")));

                    }


                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                }

            };

            saxParser.parse(SaxMountainsMain.class.getResourceAsStream("/world.xml"), handler);
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
            Element rootElement = doc.createElement("mountains");
            doc.appendChild(rootElement);
            for (Mountain mountain : mountains) {
                createMountainNode(doc, rootElement, mountain);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("mountains.xml"));

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

    private static void createMountainNode(Document doc, Element rootElement, Mountain mon) {
        // staff elements
        Element mountain = doc.createElement("mountain");
        rootElement.appendChild(mountain);

        // set attribute to staff element
        Attr mountainName = doc.createAttribute("mountainName");
        mountainName.setValue(mon.name);
        mountain.setAttributeNode(mountainName);
        Attr height = doc.createAttribute("height");
        height.setValue(mon.height);
        mountain.setAttributeNode(height);
    }

    private static class Mountain {
        String name;
        String height;

        public Mountain(String name, String height) {
            this.name = name;
            this.height = height;
        }

    }
}
