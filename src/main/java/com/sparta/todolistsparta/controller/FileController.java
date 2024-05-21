package com.sparta.todolistsparta.controller;

import com.sparta.todolistsparta.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    private FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @PostMapping("/upload")
    ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok("File uploaded successfully: " + fileService.uploadFile(file));
    }
}
