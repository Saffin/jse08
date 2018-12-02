package cz.ucl.javase.xmljsonparsing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DomCitiesMain {

    private static List<City> cities = new ArrayList<>();

    public static void main(String[] arg) {

        try {
            //country province country population cityName
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(DomCitiesMain.class.getResourceAsStream("/world.xml"));

            doc.getDocumentElement().normalize();

            Element countriesElement = (Element) doc.getElementsByTagName("countries").item(0);
            NodeList countyNodes = countriesElement.getChildNodes();
            for (int temp = 0; temp < countyNodes.getLength(); temp++) {

                Node countyNode = countyNodes.item(temp);
                if (countyNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element countyElement = (Element) countyNode;
                    String countryName = countyElement.getAttribute("countryName");

                    NodeList nList1 = countyElement.getChildNodes();
                    for (int temp1 = 0; temp1 < nList1.getLength(); temp1++) {
                        Node nNode1 = nList1.item(temp1);
                        if ("city".equalsIgnoreCase(nNode1.getNodeName())) {
                            if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                                addCity(countryName, (Element) nNode1);
                            }
                        } else if ("province".equalsIgnoreCase(nNode1.getNodeName())) {
                            NodeList provinceChildrenNodes = countyElement.getChildNodes();
                            for (int temp2 = 0; temp2 < provinceChildrenNodes.getLength(); temp2++) {
                                Node provinceChildrenNode = nList1.item(temp2);

                                if ("city".equalsIgnoreCase(provinceChildrenNode.getNodeName())) {
                                    if (provinceChildrenNode.getNodeType() == Node.ELEMENT_NODE) {
                                        addCity(countryName, (Element) provinceChildrenNode);
                                    }
                                }
                            }

                        }
                    }
                }
            }

            printResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addCity(String countryName, Element cityElement) {
        NodeList cityNodes = cityElement.getChildNodes();
        String cityName = "";
        String populationString = "0";
        for (int temp = 0; temp < cityNodes.getLength(); temp++) {

            Node cityNode = cityNodes.item(temp);
            if ("cityName".equalsIgnoreCase(cityNode.getNodeName())) {
                cityName = cityNode.getTextContent();
            } else if ("population".equalsIgnoreCase(cityNode.getNodeName())) {
                populationString = cityNode.getTextContent();
            }
        }

        cities.add(new City(countryName, cityName, new Long(populationString)));
    }


    public static void printResult() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("cities");
            doc.appendChild(rootElement);


            for (City city : cities) {
                if (city.population > 8000000) {
                    createNode(doc, rootElement, city);
                }
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("cities.xml"));

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


    private static void createNode(Document doc, Element rootElement, City city) {
        // staff elements
        Element eCity = doc.createElement("city");
        rootElement.appendChild(eCity);

        Element eCityName = doc.createElement("cityName");
        eCityName.setTextContent(city.cityName);
        eCity.appendChild(eCityName);

        Element ePopulation = doc.createElement("population");
        ePopulation.setTextContent(city.population.toString());
        eCity.appendChild(ePopulation);

        Element eCountryName = doc.createElement("countryName");
        eCountryName.setTextContent(city.countryName);
        eCity.appendChild(eCountryName);
    }


    private static class City {
        String cityName;
        Long population;
        String countryName;

        public City(String countryName, String cityName, Long population) {
            this.countryName = countryName;
            this.cityName = cityName;
            this.population = population;
        }
    }
}
