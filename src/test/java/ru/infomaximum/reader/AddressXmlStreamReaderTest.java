package ru.infomaximum.reader;

import org.junit.jupiter.api.Test;
import ru.infomaximum.entity.Address;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressXmlStreamReaderTest {

    @Test
    void readNextAddress() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testXml.xml").getFile());
        List<Address> list = new LinkedList<>();
        Address a = null;
        try (AddressXmlStreamReader reader =  new AddressXmlStreamReader(new FileInputStream(file))) {
            while ((a = reader.readNextAddress()) != null) {
                list.add(a);
            }
            assertEquals(16, list.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
