package com.borrowhut.ws.contoller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.borrowhut.ws.service.DisplayedUiCardService;

@Component
public class DisplayedUiCardsController {
	
	private final DisplayedUiCardService displayedUiCardService;
	
	@Inject
	public DisplayedUiCardsController(DisplayedUiCardService displayedUiCardService)
	{
		this.displayedUiCardService = displayedUiCardService;
	}
	
	public int getDistanceByUicId(int uicid)
	{		   
		return displayedUiCardService.getDistanceByUicId(uicid);	
		
	}

}
