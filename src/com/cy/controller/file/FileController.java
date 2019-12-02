package com.cy.controller.file;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cy.service.file.FileService;
import com.cy.utils.CyResult;

@RequestMapping("file")
@Controller
public class FileController {
	
	@Resource
	private FileService fileService;
	
	
	@ResponseBody
	@RequestMapping("/upLoad.do")
	public CyResult upLoadDemo(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,String folderName) throws Exception{
		response.setContentType("text/html");
		CyResult  result=fileService.saveFile(file,request,folderName);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/upLoadPicture.do")
	public CyResult upLoad(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response,String folderName,String pictureName) throws Exception{
		response.setContentType("text/html");
		CyResult  result=fileService.saveFilePicture(file,request,folderName,pictureName);
		return result;
	}

}
