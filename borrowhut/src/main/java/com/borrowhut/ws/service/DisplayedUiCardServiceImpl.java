package com.borrowhut.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.repository.CustomDispalyedUiCardRepository;

@Service
@Validated
public class DisplayedUiCardServiceImpl implements DisplayedUiCardService{

	@Autowired
	private CustomDispalyedUiCardRepository customDispalyedUiCardRepository;
	
	@Override
	public int getDistanceByUicId(int uicid) {
		// TODO Auto-generated method stub
		
		
		
		return customDispalyedUiCardRepository.gettDistanceByUicId(uicid);
	}

}
