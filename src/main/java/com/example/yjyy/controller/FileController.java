package com.example.yjyy.controller;

import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("FileController")
public class FileController {
    @Autowired
    private FileService fileService;

    @CrossOrigin
    @PostMapping("uploadFile")
    public WebRestResult uploadFile(MultipartFile file,String operationuser){
        WebRestResult result = fileService.uploadFile(file,operationuser);
        return result;
    }
}
