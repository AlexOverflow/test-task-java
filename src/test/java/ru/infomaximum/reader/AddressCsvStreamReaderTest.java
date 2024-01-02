package ru.infomaximum.reader;

import org.junit.jupiter.api.Test;
import ru.infomaximum.entity.Address;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressCsvStreamReaderTest {
    @Test
    void readNextAddressTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testCsv.csv").getFile());
        List<Address> list = new LinkedList<>();
        Address a = null;
        try (AddressCsvStreamReader reader = new AddressCsvStreamReader(new FileInputStream(file))) {
            while ((a = reader.readNextAddress()) != null) {
                list.add(a);
            }
            assertEquals(13, list.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
