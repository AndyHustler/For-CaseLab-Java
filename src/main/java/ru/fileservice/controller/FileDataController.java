package ru.fileservice.controller;

import jakarta.validation.Valid;
import ru.fileservice.model.FileData;
import ru.fileservice.model.FileDataRequest;
import ru.fileservice.model.ReturnId;
import ru.fileservice.service.FileDataService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/files")
public class FileDataController {

    private final FileDataService fileDataService;

    public FileDataController(FileDataService profileService) {
        this.fileDataService = profileService;
    }

    @GetMapping(value = "/{fileId:\\d+}")
    public FileData getFileData(@PathVariable int fileId) {
        return fileDataService.getFileData(fileId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnId createProfile(@Valid @RequestBody FileDataRequest request) {   
        return fileDataService.createFileData(
                request.title(),
                request.description(),
                request.creation_date(),
                request.file()
        );
    }

    @PutMapping(value = "/{fileId:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfile(
            @Valid @RequestBody FileDataRequest request,
            @PathVariable int fileId
    ) {
        fileDataService.updateFileData(
                request.title(),
                request.description(),
                request.creation_date(),
                request.file(),
                fileId
        );
    }

    @DeleteMapping(value = "/{fileId:\\d+}")
    public void deleteProfile(@PathVariable int fileId) {
        fileDataService.deleteFileData(fileId);
    }
}
