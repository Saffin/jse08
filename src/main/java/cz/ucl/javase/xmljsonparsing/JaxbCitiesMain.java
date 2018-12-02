package cz.ucl.javase.xmljsonparsing;

import cz.ucl.javase.xmljsonparsing.cities.CitiesType;
import cz.ucl.javase.xmljsonparsing.world.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.*;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JaxbCitiesMain {

    private static List<City> cities = new ArrayList<>();

    public static void main(String[] arg) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WorldType.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement element = (JAXBElement) jaxbUnmarshaller.unmarshal(JaxbCitiesMain.class.getResourceAsStream("/world.xml"));
            WorldType worldType = (WorldType) element.getValue();

            for (CountryType countryType : worldType.getCountries().getCountry()) {
                String countryName = countryType.getCountryName();
                if (!"Switzerland".equals(countryName)) {
                    continue;
                }

                for (Object ob : countryType.getCountryNamesOrCityOrEthnicgroups()) {
                    if (ob instanceof CityType) {
                        addCity(countryName, (CityType) ob);
                    } else if (ob instanceof ProvinceType) {
                        ProvinceType province = (ProvinceType) ob;
                        for (Object ob1 : province.getContent()) {
                            if (ob1 instanceof JAXBElement){
                                Object value = ((JAXBElement) ob1).getValue();
                                if (value instanceof CityType) {
                                    addCity(countryName, (CityType) value);
                                }
                            }
                            if (ob1 instanceof CityType) {
                                addCity(countryName, (CityType) ob1);
                            }
                        }
                    }

                }
            }

            printResult();
            printResultsJaxb();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addCity(String countryName, CityType city) {
        CityType cityType = city;
        Long population = getPopulation(cityType);
        cities.add(new City(countryName, getCityName(cityType), population));
    }

    private static Long getPopulation(CityType cityType) {

        if (cityType.getPopulation() == null || cityType.getPopulation().size() == 0) {
            return 0L;
        }

        List<PopulationType> populations = new ArrayList<>(cityType.getPopulation());

        Collections.sort(populations, new Comparator<PopulationType>() {
            @Override
            public int compare(PopulationType o1, PopulationType o2) {
                return - o1.getYear().compareTo(o2.getYear());
            }
        });
        return new Long(populations.get(0).getValue());
    }

    private static String getCityName(CityType cityType) {
        List<String> cityNames = cityType.getCityName();
        if (cityNames == null) {
            return null;
        }
        return cityNames.get(0);
    }

    public static void printResultsJaxb() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(CitiesType.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        City city = cities.get(0);
        cz.ucl.javase.xmljsonparsing.cities.CityType cityType
                = new cz.ucl.javase.xmljsonparsing.cities.CityType();
        cityType.setCityName(city.cityName);
        cityType.setCountryName(city.countryName);
        cityType.setPopulation(new cz.ucl.javase.xmljsonparsing.cities.PopulationType());
        cityType.getPopulation().setValue(city.population.intValue());

        CitiesType cities = new CitiesType();
        cities.getCity().add(cityType);

        jaxbMarshaller.marshal(cities,new FileOutputStream("cities_jaxb.xml"));


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
                createNode(doc, rootElement, city);
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

        if (city.population != 0) {
            Element ePopulation = doc.createElement("population");
            ePopulation.setTextContent(city.population.toString());
            eCity.appendChild(ePopulation);
        }

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
