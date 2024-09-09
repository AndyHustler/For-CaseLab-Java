package ru.fileservice.exception;

public class FileDataNotFoundException extends RuntimeException {

    private final int personId;

    public FileDataNotFoundException(int personId) {
        this.personId = personId;
    }

    @Override
    public String getMessage() {
        return "File with id = " + personId + " not found";
    }
}
