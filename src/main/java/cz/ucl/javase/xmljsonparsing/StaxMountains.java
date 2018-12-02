package cz.ucl.javase.xmljsonparsing;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaxMountains {

    private static List<Mountain> mountains = new ArrayList<>();

    public static void main(String args[]) {

        try {
            XMLEventReader eventReader = XMLInputFactory.newInstance().createXMLEventReader(
                    StaxMountains.class.getResourceAsStream("/world.xml"));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("mountains")) {
                        readMountains(eventReader);
                    }
                }
            }

            printResultStreamWriter();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult() throws XMLStreamException, IOException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        XMLEventWriter writer = factory.createXMLEventWriter(
                new FileWriter("mountains-stax.xml"));

        XMLEvent event = eventFactory.createStartDocument();
        writer.add(event);

        event = eventFactory.createStartElement("", "","mountains");
        writer.add(event);

        for (Mountain mountain: mountains) {
            StartElement mountainElement = eventFactory.createStartElement("", "", "mountain");
            writer.add(mountainElement);
            writer.add(eventFactory.createAttribute("height", mountain.height));
            writer.add(eventFactory.createAttribute("mountainName", mountain.name));
            writer.add(eventFactory.createEndElement("", "", "mountain"));
        }

        event = eventFactory.createEndElement("", "", "mountains");
        writer.add(event);
        writer.close();
    }

    private static void printResultStreamWriter() throws XMLStreamException, IOException {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileWriter("mountains-stax.xml"));

        writer.writeStartDocument();

        writer.writeStartElement("mountains");

        for (Mountain mountain: mountains) {
            writer.writeStartElement("mountain");
            writer.writeAttribute("height", mountain.height);
            writer.writeAttribute("mountainName", mountain.name);
            writer.writeEndElement();
        }

        writer.writeEndElement();
        writer.writeEndDocument();
        writer.close();
    }

    private static void readMountains(XMLEventReader eventReader) throws XMLStreamException {

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (startElement.getName().getLocalPart().equals("mountain")) {
                    String name = startElement.getAttributeByName(QName.valueOf("mountainName")).getValue();
                    String height = startElement.getAttributeByName(QName.valueOf("height")).getValue();
                    Mountain mountain = new Mountain(name, height);
                    mountains.add(mountain);
                }
            }

            if (event.isEndElement()
                    && event.asEndElement().getName().getLocalPart().equals("mountains")) {
                break;
            }
        }
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
