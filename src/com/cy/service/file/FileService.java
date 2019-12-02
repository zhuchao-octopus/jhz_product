package com.cy.service.file;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cy.utils.CyResult;

public interface FileService {

	CyResult saveFile(MultipartFile file, HttpServletRequest request, String folderName) throws Exception;
	
	CyResult saveFilePicture(MultipartFile file, HttpServletRequest request, String folderName,String pictureName) throws Exception;

}
