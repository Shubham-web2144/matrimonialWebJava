package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class UploadImag {
    

    public static void uploadImageDir(String uploadDir, String filename, MultipartFile multipartFile) throws IOException {
        Path path = Paths.get(uploadDir);
        if(!Files.exists(path)) {
            Files.createDirectories(path);

        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path uploadPath = path.resolve(filename);
            long copy = Files.copy(inputStream, uploadPath,StandardCopyOption.REPLACE_EXISTING );

            System.out.println("Folder Path " + uploadPath);
            System.out.println("Logn " + copy);
        }catch(IOException exception) {
            exception.printStackTrace();
        }

        System.out.println("Folder Path " + path);
    }

}
