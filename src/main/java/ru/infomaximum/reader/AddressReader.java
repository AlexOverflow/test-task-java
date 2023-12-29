package ru.infomaximum.reader;

import ru.infomaximum.entity.Address;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/* Предпологалось использовать как общий интерфейс  для AddressXmlFileReader и  AddressCsvFileReader */
public interface AddressReader extends AutoCloseable  {
    Address readNextAddress() throws XMLStreamException, IOException;
}
