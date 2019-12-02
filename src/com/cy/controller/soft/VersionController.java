package com.cy.controller.soft;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.domain.soft.SoftVersion;
import com.cy.service.soft.VersionService;
import com.cy.utils.CyResult;

@RequestMapping("/version")
@Controller
public class VersionController {

	  @Resource
	  private VersionService versionService;
	  
	  @RequestMapping("/loadLastVersion.do")
	  @ResponseBody
	   public CyResult loadLastVersion(SoftVersion version){
		  CyResult result=versionService.loadLastVersion(version);
		  return result;
	  }
	  
	  
	  
	  @RequestMapping("/loadAllVersion.do")
	  @ResponseBody
	  public  CyResult loadAllVersion(SoftVersion version){
		  CyResult result=versionService.loadAllVersion(version);
		  return result;
	  }
	  
	  @RequestMapping("/addSoftware.do")
	  @ResponseBody
	  public  CyResult addSoftware(SoftVersion version){
		  CyResult result=versionService.addSoftware(version);
		  return result;
	  }
	  
	  @RequestMapping("/deleteVersion.do")
	  @ResponseBody
	  public  CyResult deleteVersion(SoftVersion version){
		  CyResult result=versionService.deleteVersion(version);
		  return result;
	  }
	  

	  @RequestMapping("/editVersion.do")
	  @ResponseBody
	  public  CyResult editVersion(SoftVersion version){
		  CyResult result=versionService.editVersion(version);
		  return result;
	  }
	  
}
