package ru.fileservice.repository;

import java.util.Optional;

import ru.fileservice.model.FileData;
import ru.fileservice.model.ReturnId;

public interface FileDataRepository {

    Optional<FileData> getFileDataById(int id);

    ReturnId insertFileData(String title, String description, String creation_date, byte[] file);

    void updateFileData(String title, String description, String creation_date, byte[] file, int id);

    void deleteFileDataById(int id);
}