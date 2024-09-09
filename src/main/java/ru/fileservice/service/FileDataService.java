package ru.fileservice.service;

import ru.fileservice.model.FileData;
import ru.fileservice.model.ReturnId;

public interface FileDataService {

    FileData getFileData(int fileId);

    ReturnId createFileData(String title, String description, String creation_date, byte[] file);

    void updateFileData(String title, String description, String creation_date, byte[] file, int id);

    void deleteFileData(int id);
}
