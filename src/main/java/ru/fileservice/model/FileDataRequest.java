package ru.fileservice.model;

import jakarta.validation.constraints.NotNull;

public record FileDataRequest(
        @NotNull
        String title,
        @NotNull
        String description,
        @NotNull
        String creation_date,
        @NotNull
        byte[] file
) {
}