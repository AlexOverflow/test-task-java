package ru.infomaximum.utils;

import ru.infomaximum.enums.FileExtension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class FileValidator {
    public static FileExtension getFileExtension(File file) throws IllegalArgumentException, FileNotFoundException {
        if (!file.isFile()) throw new FileNotFoundException();
        String fileName = file.getName().toLowerCase();
        FileExtension[] extensions = FileExtension.values();
        return Arrays.stream(extensions)
                .filter(e -> fileName.endsWith(e.getFileExtension()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
