package com.blackboard.backEnd.service;

import com.blackboard.backEnd.model.LogInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import static com.blackboard.backEnd.model.LogInfo.prop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The StorageService class is used to initialize a directory where uploaded files will be stored. The
 * class also has methods for loading and deleting files from the "root" dir. The parent directory
 * is the same as the mysql uploads directory where the base data file is.
 */
@Service
public class StorageService {

    private final Path rootLocation = Paths.get(prop.getProperty("baseDB"));

    /**
     * This method takes a file and copies it to the storage location
     * @param file the multipart file to be stored (mainly used for handleFileUpload endpoint)
     */
    public void store(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            LogInfo.sendError("store error...");
            throw new RuntimeException("store error!");
        }
    }

    /**
     * deleteAll clears the upload folder each time the server is started up
     */
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    /**
     * init method which creates the directory each time the server is started up
     */
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }


}
