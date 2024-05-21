package com.sparta.todolistsparta.service;

import com.sparta.todolistsparta.exception.UnacceptableExtensionsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService {
    private final Path fileStorageLocation;
    private final List<String> allowedFileTypes = Arrays.asList("image/jpeg", "image/png", "image/jpg");
    public FileService(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch(Exception e) {
            throw new RuntimeException("디렉토리를 생성하지 못하지 못했습니다.");
        }
    }

    public String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();

        if (!allowedFileTypes.contains(fileType)) {
            throw new UnacceptableExtensionsException();
        }

        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);

            return fileName;
        } catch(IOException e) {
            throw new RuntimeException("해당 파일을 찾을 수 없습니다.");
        }
    }
}
