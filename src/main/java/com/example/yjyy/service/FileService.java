package com.example.yjyy.service;

import com.example.yjyy.result.WebRestResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    WebRestResult uploadFile(MultipartFile file,String operationuser);
}
