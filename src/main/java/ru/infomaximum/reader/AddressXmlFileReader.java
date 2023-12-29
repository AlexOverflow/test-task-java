package ru.infomaximum.reader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.infomaximum.entity.Address;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.*;

public class AddressXmlFileReader implements AddressReader {
    private InputStream stream;
    private XMLStreamReader xmlStreamReader;
    XmlMapper mapper;
    public AddressXmlFileReader(File file) throws FileNotFoundException, XMLStreamException {
        InputStream stream = new FileInputStream(file);
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        xmlStreamReader = xmlInputFactory.createXMLStreamReader(stream);
        mapper = new XmlMapper();
    }

    /*  TODO: Решить проблему со Stax XML парсером.
         На данный момент XML парсер работает с ошибками, поэтому на данный момент доступно только чтение из csv файла   */
    public Address readNextAddress() throws XMLStreamException, IOException {
        Address obj = null;
        if (xmlStreamReader.hasNext()) {
            xmlStreamReader.next();
            if (xmlStreamReader.getLocalName().equals("root")) return readNextAddress();
            obj = mapper.readValue(xmlStreamReader, Address.class);
        }
        return obj;
    }

    @Override
    public void close() throws Exception {

    }
}
