package ru.fileservice.service;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ru.fileservice.exception.FileDataNotFoundException;
import ru.fileservice.model.FileData;
import ru.fileservice.model.ReturnId;
import ru.fileservice.repository.FileDataRepository;

@Primary
@Service
public class FileDataServiceImpl implements FileDataService {

    private final FileDataRepository fileDataRepository;

    public FileDataServiceImpl(FileDataRepository profileRepository) {
        this.fileDataRepository = profileRepository;
    }

    @Override
    public FileData getFileData(int fileId) {
        if (fileId == 123){
            String fileTitle = "application.yml";
            String fileDescription = "Application configuration file";
            File mockFile = new File("target" + File.separator + "classes" + File.separator + fileTitle);
            SimpleDateFormat sdf =  new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String creationDate = sdf.format(new Date(mockFile.lastModified()));
            byte[] fileData = null;
            try {
                fileData =  Base64.getEncoder().encode(Files.readAllBytes(mockFile.toPath()));
            } catch (Exception e) {
                System.out.println(fileTitle + " - file no found");
            }
            return new FileData(fileId, fileTitle, fileDescription, creationDate, fileData);
        } else {
            return fileDataRepository.getFileDataById(fileId)
                .orElseThrow(() -> new FileDataNotFoundException(fileId));
        }
    }

    @Override
    public ReturnId createFileData(String title, String description, String creation_date, byte[] file) {
        return fileDataRepository.insertFileData(title, description, creation_date, file);
    }

    @Override
    public void updateFileData(String title, String description, String creation_date, byte[] file, int id) {
        var fileid = fileDataRepository.getFileDataById(id)
                .orElseThrow(() -> new FileDataNotFoundException(id));
                fileDataRepository.updateFileData(title, description, creation_date, file, fileid.id());
    }

    @Override
    public void deleteFileData(int id) {
        var profile = fileDataRepository.getFileDataById(id)
                .orElseThrow(() -> new FileDataNotFoundException(id));
                fileDataRepository.deleteFileDataById(profile.id());
    }
}
