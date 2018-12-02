package cz.ucl.javase.xmljsonparsing;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaxSeasMain {

    private static List<String> seas = new ArrayList<>();

    public static void main(String[] arg) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                private String indiaId;
                private String actualSea;
                private boolean checkSeaElement = false;

                @Override
                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("COUNTRY") && attributes.getValue("countryName").equals("India")) {
                        indiaId = attributes.getValue("id");
                    }

                    if (qName.equalsIgnoreCase("SEA")) {
                        actualSea = attributes.getValue("seaName");
                        checkSeaElement = true;
                    }
                    if (checkSeaElement && qName.equalsIgnoreCase("LOCATED")) {
                        if (indiaId != null && indiaId.equals(attributes.getValue("country"))) {
                            seas.add(actualSea);
                            checkSeaElement = false;
                        }
                    }

                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("SEA")) {
                        checkSeaElement = false;
                    }

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                }

            };

            saxParser.parse(SaxSeasMain.class.getResourceAsStream("/world.xml"), handler);
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
            for (String sea : seas) {
                createNode(doc, rootElement, sea);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("seas.xml"));

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

    private static void createNode(Document doc, Element rootElement, String name) {
        // staff elements
        Element mountain = doc.createElement("sea");
        rootElement.appendChild(mountain);

        // set attribute to staff element
        Attr mountainName = doc.createAttribute("seaName");
        mountainName.setValue(name);
        mountain.setAttributeNode(mountainName);

    }


}
