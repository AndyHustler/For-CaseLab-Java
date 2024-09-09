package ru.fileservice.model;

public record FileData(
        int id,
        String title,
        String description,
        String creation_date,
        byte[] file
) {
}
