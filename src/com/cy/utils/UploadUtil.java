package com.cy.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;



public class UploadUtil {
	/**
	 * 上传的工具类
	 * 
	 * @param file
	 *            上传的文件
	 * @param request
	 *            请求
	 * @param folderName
	 *            存放的文件夹地址名
	 * @throws Exception 
	 */
	public static Map<String,Object> upload(MultipartFile file, HttpServletRequest request,
			String folderName) throws Exception {
		
		
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;             // 获得文件：         
		//file = (MultipartFile) multipartRequest.getFileMap(); 
		
		Map<String,Object> map=new HashMap<String, Object>();
		System.out.println("folderName:"+folderName);

		// 存放地址
		String path = request.getSession().getServletContext().getRealPath("");
		//获取第二个斜杠位置
		int n = path.indexOf(File.separator, path.indexOf(File.separator)+1);
		//新地址
		String newpath=path.substring(0,n+1)+folderName;
//		// 文件名字
//		String filename = file.getOriginalFilename();
		System.out.println(newpath);
		
		// 生成新的文件名
		// 取源文件的原始文件文
		String oldName = file.getOriginalFilename();
		// 新的文件名字
		// uuid
		// UUID.randomUUID();
		oldName = URLDecoder.decode(oldName,"utf-8");
		//oldName = new String(oldName.getBytes("ISO-8859-1"),"utf-8");
		String filename = CyUtil.getName()+"";
		System.out.println(oldName);
		int index=oldName.indexOf(".");
		if(index==-1){
			filename=filename.toString();
		}else{
		    filename = filename + oldName.substring(oldName.lastIndexOf("."));
		    String suffix=filename.substring(filename.lastIndexOf("."));
			map.put("suffix", suffix);
		}
		System.out.println(filename);
		
		//文件后缀
		
		// 获取上传文件
		
		File targetFile = new File(newpath, filename);
		// 文件大小
		Long size=targetFile.length()/1024;
		
		// 存放目录是否存在
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 存放文件是否存在
		String targetFileAdd = newpath + File.separator + filename;

		if (new File(targetFileAdd).isFile()) {
			
			BufferedImage bi = ImageIO.read(targetFile);
			if(bi != null){
				map.put("msg","图片存在");
				return map;
			}
			
			map.put("msg","文件存在");
			return map;
		}

		// 保存文件
		try {
			file.transferTo(targetFile);
			String Path1=request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/"));
//			String servletPath=Path1.substring(0,Path1.lastIndexOf("/"));
			String url=request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf(":")+5)+"/";
			size=targetFile.length();
			map.put("path", newpath);
			map.put("filename", filename);
			map.put("size", size);
			map.put("url", url);
			map.put("targetFile", targetFile);
			map.put("oldName", oldName);
			map.put("targetFileAdd",targetFileAdd);
		} catch (Exception e) {
		    (new File(targetFileAdd)).delete();
		    System.out.println("文件上传异常,上传部分已经删除");
		    e.printStackTrace();
		}
		
		map.put("msg", "保存成功");
		return map;
	}
	
	public static Map<String,Object> upload1(MultipartFile file, HttpServletRequest request,
			String folderName,String pictureName) throws Exception {
		
		
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;             // 获得文件：         
				//file = (MultipartFile) multipartRequest.getFileMap(); 
				
				Map<String,Object> map=new HashMap<String, Object>();
				System.out.println("folderName:"+folderName);

				// 存放地址
				String path = request.getSession().getServletContext().getRealPath("");
				//获取第二个斜杠位置
				int n = path.indexOf(File.separator, path.indexOf(File.separator)+1);
				//新地址
				String newpath=path.substring(0,n+1)+folderName;
//				// 文件名字
//				String filename = file.getOriginalFilename();
				System.out.println(newpath);
				
				// 生成新的文件名
				// 取源文件的原始文件文
				String oldName = file.getOriginalFilename();
				// 新的文件名字
				// uuid
				// UUID.randomUUID();
				oldName = URLDecoder.decode(oldName,"utf-8");
				//oldName = new String(oldName.getBytes("ISO-8859-1"),"utf-8");
				String filename = pictureName;  
				System.out.println(oldName);
				int index=oldName.indexOf(".");
				if(index==-1){
					filename=filename.toString();
				}else{
				    filename = filename + oldName.substring(oldName.lastIndexOf("."));
				    String suffix=filename.substring(filename.lastIndexOf("."));
					map.put("suffix", suffix);
				}
				System.out.println(filename);
				
				//文件后缀
				
				// 获取上传文件
				
				File targetFile = new File(newpath, filename);
				// 文件大小
				Long size=targetFile.length()/1024;
				
				// 存放目录是否存在
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 存放文件是否存在
				String targetFileAdd = newpath + File.separator + filename;
				System.out.println(targetFileAdd);
				if (new File(targetFileAdd).isFile()) {
					
					BufferedImage bi = ImageIO.read(targetFile);
					System.out.println("bi:"+bi);
					if(bi != null){
						new File(targetFileAdd).delete();

						// 保存文件
						try {
							file.transferTo(targetFile);
							String Path1=request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/"));
//							String servletPath=Path1.substring(0,Path1.lastIndexOf("/"));
							String url=request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf(":")+5)+"/";
							size=targetFile.length();
							map.put("path", newpath);
							map.put("filename", filename);
							map.put("size", size);
							map.put("url", url);
							map.put("targetFile", targetFile);
							map.put("oldName", oldName);
							map.put("targetFileAdd",targetFileAdd);
						} catch (Exception e) {
						    (new File(targetFileAdd)).delete();
						    System.out.println("文件上传异常,上传部分已经删除");
						    e.printStackTrace();
						}
						
						map.put("msg", "保存成功");
						return map;
					}
					
					map.put("msg","文件存在");
					return map;
				}

				// 保存文件
				try {
					file.transferTo(targetFile);
					String Path1=request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/"));
//					String servletPath=Path1.substring(0,Path1.lastIndexOf("/"));
					String url=request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf(":")+5)+"/";
					size=targetFile.length();
					map.put("path", newpath);
					map.put("filename", filename);
					map.put("size", size);
					map.put("url", url);
					map.put("targetFile", targetFile);
					map.put("oldName", oldName);
					map.put("targetFileAdd",targetFileAdd);
				} catch (Exception e) {
				    (new File(targetFileAdd)).delete();
				    System.out.println("文件上传异常,上传部分已经删除");
				    e.printStackTrace();
				}
				
				map.put("msg", "保存成功");
				return map;
	}
    
}
