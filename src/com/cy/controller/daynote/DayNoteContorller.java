package com.cy.controller.daynote;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.service.daynote.DayNoteService;
import com.cy.utils.CyResult;

@Controller
@RequestMapping("/note")
public class DayNoteContorller {
	
	
	@Resource
	private DayNoteService service;
	
	
	@RequestMapping("/loadDayNote.do")
	@ResponseBody
	public CyResult loadDayNote() throws ParseException{
		CyResult result=service.loadDayNotes();
		return result;
	}

}
