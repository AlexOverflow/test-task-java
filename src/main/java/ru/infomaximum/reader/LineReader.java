package ru.infomaximum.reader;
import java.io.InputStream;
import java.util.Scanner;

public class LineReader {
    private final Scanner scanner;
    private final char endReadingSymbol;
    public LineReader (InputStream stream, char endReadingSymbol) {
        this.scanner = new Scanner(stream);
        this.endReadingSymbol = endReadingSymbol;
    }
    public String readLine() {
        String line = scanner.nextLine();
        return line.equals(String.valueOf(endReadingSymbol)) ? null : line;
    }
}
