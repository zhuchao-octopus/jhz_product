package com.cy.service.daynote;

import java.text.ParseException;

import com.cy.utils.CyResult;

public interface DayNoteService {

	CyResult loadDayNotes() throws ParseException;

}
