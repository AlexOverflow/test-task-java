package ru.infomaximum.utils;

import org.junit.jupiter.api.Test;
import ru.infomaximum.enums.FileExtension;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FileValidatorTest {

    @Test
    void shouldReturnFileExtensionForSupportedFiles() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File fileCsv = new File(classLoader.getResource("testCsv.csv").getFile());
        File fileXml = new File(classLoader.getResource("testXml.xml").getFile());
        FileExtension xmlExt = FileValidator.getFileExtension(fileXml);
        FileExtension csvExt = FileValidator.getFileExtension(fileCsv);
        assertEquals(xmlExt, FileExtension.XML);
        assertEquals(csvExt, FileExtension.CSV);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenFilesNotSupported() {
        ClassLoader classLoader = getClass().getClassLoader();
        File fileTxt = new File(classLoader.getResource("testTxt.txt").getFile());
        assertThrows(IllegalArgumentException.class, () -> FileValidator.getFileExtension(fileTxt));
    }
}