package ru.infomaximum.enums;

public enum FileExtension {
    XML("xml"),
    CSV("csv");
    private final String fileExtension;

    FileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    @Override
    public String toString() {
        return this.fileExtension;
    }
}
