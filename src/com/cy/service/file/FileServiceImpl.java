package com.cy.service.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cy.utils.CyResult;
import com.cy.utils.UploadUtil;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public CyResult saveFile(MultipartFile file, HttpServletRequest request, String folderName) throws Exception {
		
			CyResult result = new CyResult();
			// 保存图片地址
			String fileUrl = "";
			// 保存图片名字
			String imgName = "";
			// 文件夹名字
			//String folderName = "adv";
			// 接收上传结果
			Map<String, Object> map = null;
		

			map = UploadUtil.upload(file, request, folderName);
			// 如果返回结果不是保存成功
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setCode(1);
				result.setMsg("上传失败!");
				return result;
			}
			// 获取文件后缀
			// String suffix = map.get("suffix").toString();
			// 获取上传文件二进制
			//File targetFile = (File) map.get("targetFile");
			fileUrl = map.get("url").toString() + folderName + "/" + map.get("filename").toString();
			result.setData(fileUrl);
			return result;
			
			
				
	      
		
	}
	
	@Override
	public CyResult saveFilePicture(MultipartFile file, HttpServletRequest request, String folderName,String pictureName) throws Exception {
		
			CyResult result = new CyResult();
			// 保存图片地址
			String fileUrl = "";
			// 保存图片名字
			String imgName = "";
			// 文件夹名字
			//String folderName = "adv";
			// 接收上传结果
			Map<String, Object> map = null;
		

			map = UploadUtil.upload1(file, request, folderName,pictureName);
			// 如果返回结果不是保存成功
			if (!map.get("msg").toString().equals("保存成功")) {
				result.setCode(0);
				result.setMsg("上传失败!");
				return result;
			}
			// 获取文件后缀
			// String suffix = map.get("suffix").toString();
			// 获取上传文件二进制
			//File targetFile = (File) map.get("targetFile");
			fileUrl = map.get("url").toString() + folderName + "/" + map.get("filename").toString();
			result.setData(fileUrl);
			return result;
			
			
				
	      
		
	}

}
