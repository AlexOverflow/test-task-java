package ru.infomaximum.reader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.infomaximum.entity.Address;
import ru.infomaximum.exception.AddressReaderException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import java.io.*;

public class AddressXmlStreamReader implements AddressReader {

    private final XMLStreamReader xmlStreamReader;
    private final XmlMapper mapper;

    public AddressXmlStreamReader(InputStream inputStream) throws AddressReaderException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        try {
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
        } catch (XMLStreamException e) {
            throw new AddressReaderException("Ошибка при открытии XML файла");
        }
        mapper = new XmlMapper();
    }

    public Address readNextAddress() throws AddressReaderException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        try {
            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();
                if (event == XMLEvent.START_ELEMENT) {
                    String localName = xmlStreamReader.getLocalName();
                    if (localName.equals("item")) {
                        return mapper.readValue(xmlStreamReader, Address.class);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            throw new AddressReaderException("Ошибка при обработке XML файла");
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        if (xmlStreamReader != null) xmlStreamReader.close();
    }
}
