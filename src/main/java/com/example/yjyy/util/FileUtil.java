package com.example.yjyy.util;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileUtil {

	//获取新文件命名
	public static String getFileName(String oldFileName, String operationUser ) {
		String datestr = Tools.dateToStr(new Date());
		String suffix = oldFileName.substring(oldFileName.indexOf(".")).toLowerCase();
		String newFileName = "/userfiles/" + operationUser + "/photo/"+ datestr.substring(0, 4) + "/" + datestr.substring(4, 6)+ "/" + UUIDUtil.randomUUID() + suffix;
		return newFileName;
	}

	//保存文件
	public static String writeFile(MultipartFile file, String fileName ,String fileUploadLocation)throws IOException{
		//生成上传路径
		String realPath = fileUploadLocation + "/" + fileName;

		File newFile = new File(realPath);

		//判断文件目录是否存在
		if (!newFile.getParentFile().exists()) {
			newFile.getParentFile().mkdirs();
		}

		if (!newFile.exists()) {
			newFile.createNewFile();
		}
		//保存文件
		file.transferTo(newFile);
		return realPath;
	}

	//删除文件
	public static void delFile(String fileUrl) {
		if(!Tools.isEmpty(fileUrl)){
			File file = new File(fileUrl);
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		}
	}

	//检查文件后缀
	public static Boolean checksSuffix(String fileName,String fileType){
		if (fileName.indexOf(".") == -1) {
			return false;
		}
		String suffix = fileName.substring(fileName.indexOf(".")).toLowerCase();

		if (fileType.indexOf(suffix) == -1) {
			return false;
		}
		return true;
	}


	//检查文件大小
	public static Boolean checkFileSize(byte[] bytes ,String fileSizeLimit){
		int fileSize = Integer.parseInt(fileSizeLimit);
		if (bytes.length > fileSize) {
			return false;
		}
		else
			return true;
	}

	//检查文件是否存在
	public static boolean existFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	public static void main(String args[]){
		System.out.println(Tools.dateToStr(new Date()));
	}
}
