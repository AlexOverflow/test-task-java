package ru.infomaximum.reader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.infomaximum.entity.Address;

import java.io.*;
import java.util.Iterator;

public class AddressCsvStreamReader implements AddressReader {
    private final BufferedReader reader;
    private final Iterator<Address> iterator;

    public AddressCsvStreamReader(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        CsvToBean<Address> csvReader = new CsvToBeanBuilder<Address>(reader)
                .withType(Address.class)
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build();
        iterator = csvReader.iterator();
    }
    public Address readNextAddress() {
        Address obj = null;
        if(iterator.hasNext()) obj = iterator.next();
        return obj;
    }

    public void close() throws IOException {
        if (reader !=  null) reader.close();
    }
}
