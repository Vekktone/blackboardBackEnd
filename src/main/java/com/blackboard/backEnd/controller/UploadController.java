package com.blackboard.backEnd.controller;

import com.blackboard.backEnd.model.LogInfo;
import com.blackboard.backEnd.service.StudentService;
import com.blackboard.backEnd.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * This controller handles file uploads on the web page.
 */
@RequestMapping("/upload")
@RestController
public class UploadController {

    @Autowired
    StorageService storageService;

    @Autowired
    StudentService studentService;


    // uploaded file name
    private String fileName;

    private List<String> files = new ArrayList<String>();

    /**
     * handleFileUpload endpoint calls storage service to store the file in memory
     * @param file this is the received data from the frontend, which is a file to be uploaded to the server
     */
    @PostMapping("/pushFile")
    @CrossOrigin(origins = "http://localhost:4200")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());
            fileName = file.getOriginalFilename();

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            LogInfo.sendError(message);

            this.studentService.parseFileUpdateDB(fileName);

        } catch (Exception e) {
            message = "We failed to upload " + file.getOriginalFilename() + "!";
            LogInfo.sendError(message);
        }
    }
}
