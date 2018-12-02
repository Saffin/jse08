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
import java.util.*;

public class SaxBordersMain {

    private static Map<String,String> countriesById = new HashMap<>();
    private static Set<Border> bourderSet = new HashSet<>();


    public static void main(String[] arg) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                private String actualCountryId;

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("COUNTRY")) {
                        actualCountryId = attributes.getValue("id");
                        countriesById.put(attributes.getValue("id"), attributes.getValue("countryName"));
                    }

                    if (qName.equalsIgnoreCase("BORDER")) {
                        bourderSet.add(
                                new Border(new Double(attributes.getValue("length")),
                                        attributes.getValue("country"), actualCountryId));

                    }

                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {


                }

                public void characters(char ch[], int start, int length) throws SAXException {

                }

            };

            saxParser.parse(SaxBordersMain.class.getResourceAsStream("/world.xml"), handler);
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
            Element rootElement = doc.createElement("borders");
            doc.appendChild(rootElement);

            List<Border> borders = new ArrayList<>(bourderSet);
            Collections.sort(borders, new Comparator<Border>() {
                @Override
                public int compare(Border o1, Border o2) {
                    return - o1.length.compareTo(o2.length) ;
                }
            });

            for (int i = 0; i < 7 && i < borders.size(); i++) {
                createNode(doc, rootElement, borders.get(i));
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("borders.xml"));

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

    private static void createNode(Document doc, Element rootElement, Border border) {
        // staff elements
        Element bourderNode = doc.createElement("border");
        rootElement.appendChild(bourderNode);

        // set attribute to staff element
        Attr attrLength = doc.createAttribute("length");
        attrLength.setValue(border.length.toString());
        bourderNode.setAttributeNode(attrLength);

        Element country1 = doc.createElement("countryName");
        country1.setTextContent(countriesById.get(border.country1));
        bourderNode.appendChild(country1);

        Element country2 = doc.createElement("countryName");
        country2.setTextContent(countriesById.get(border.country2));
        bourderNode.appendChild(country2);

    }


    private static class Border {
        private Double length;
        private String country1;
        private String country2;

        public Border(Double length, String country1, String country2) {
            this.length = length;
            this.country1 = country1;
            this.country2 = country2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Border bourder = (Border) o;

            if (length.compareTo(bourder.length) != 0) return false;
            if ((country1.equals(bourder.country1) && country2.equals(bourder.country2)) ||
                    (country1.equals(bourder.country2) && country2.equals(bourder.country1))) {
                return true;
            }

            return false;

        }

        @Override
        public int hashCode() {
            int result = length.hashCode();
            result = 31 * result + (country1 != null ? country1.hashCode() : 0);
            result = result + (country2 != null ? country2.hashCode() : 0);
            return result;
        }
    }
}
