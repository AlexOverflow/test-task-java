package ru.infomaximum.reader;
import ru.infomaximum.entity.Address;
import ru.infomaximum.exception.AddressReaderException;

public interface AddressReader extends AutoCloseable {
    Address readNextAddress() throws AddressReaderException;
}
