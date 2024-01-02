package ru.infomaximum.reader;

import ru.infomaximum.enums.FileExtension;
import ru.infomaximum.exception.AddressReaderException;

import java.io.InputStream;

public class AddressReaderFactory {

    public AddressReader createAddressReader (FileExtension extension, InputStream stream) throws AddressReaderException, IllegalArgumentException {
        switch (extension) {
            case CSV:
                return new AddressCsvStreamReader(stream);
            case XML:
                return new AddressXmlStreamReader(stream);
            default: throw new IllegalArgumentException();
        }
    }
}
