package com.example.yjyy.service.impl;

import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.service.FileService;
import com.example.yjyy.util.FileUtil;
import com.example.yjyy.util.Tools;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private final String fileUploadLocation = "C://pictures/";
    private final String fileType = ".jpg,.jpeg,.png,.bmp";
    private final String fileSizeLimit = "10485760";

    @Override
    public WebRestResult uploadFile(MultipartFile file, String operationuser) {
        WebRestResult result = new WebRestResult();
        try{
            if (Tools.isEmpty(operationuser)) {
                result.setResult(WebRestResult.FAILURE);
                result.setMessage("参数operationUser不能为空！");
                return result;
            }

            String filePath = "";
            String realPath = "";

            if (!file.isEmpty()) {
                try {
                    String oldFileName = file.getOriginalFilename();
                    if(Tools.isEmpty(oldFileName)){
                        result.setResult(WebRestResult.FAILURE);
                        result.setMessage("文件格式错误：无文件名！");
                        return result;
                    }
                    if(!FileUtil.checksSuffix(oldFileName,fileType)){
                        result.setResult(WebRestResult.FAILURE);
                        result.setMessage("文件格式错误：文件格式错误：非法后缀名！");
                        return result;
                    }
                    byte[] bytes = file.getBytes();
                    if(!FileUtil.checkFileSize(bytes,fileSizeLimit)){
                        result.setResult(WebRestResult.FAILURE);
                        result.setMessage("文件太大");
                        return result;
                    }

                    //生成新文件名
                    String newFileName = FileUtil.getFileName(oldFileName,operationuser);
                    realPath = FileUtil.writeFile(file,newFileName,fileUploadLocation);
                    if(realPath != null)
                        filePath = newFileName;
                } catch (Exception e) {
                    FileUtil.delFile(realPath);
                    result.setMessage("上传失败," + e.getMessage());
                    result.setResult(WebRestResult.FAILURE);
                }
            } else {
                result.setMessage("文件不能为空！");
                result.setResult(WebRestResult.FAILURE);
            }
            result.setMessage("http://39.106.171.39:8080/pictures"+filePath);
            result.setResult(WebRestResult.SUCCESS);

        }catch (Exception e) {
            result.setResult(WebRestResult.FAILURE);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
