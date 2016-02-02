package com.borrowhut.ws.service;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.exceptions.base.MockitoException;
import org.mockito.runners.MockitoJUnitRunner;

import com.borrowhut.ws.domain.MyTrustCircle;
import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.exception.PartyNotFoundException;
import com.borrowhut.ws.repository.MyTrustCircleRepository;
import com.borrowhut.ws.repository.PartyRepository;

@RunWith(MockitoJUnitRunner.class)
public class PartyServiceImplTest {
	
	
	@Mock
	private PartyRepository partyRepository;
	
	@Mock
	private MyTrustCircleRepository myTrustCircleRepository;
	
	private PartyServiceImpl partyServiceImpl;
	
	@Before
	public void setUp()  throws MockitoException
	{
		
		partyServiceImpl =new PartyServiceImpl();
		partyServiceImpl.setPartyRepository(partyRepository);
		partyServiceImpl.setMyTrustCircleRepository(myTrustCircleRepository);
		
	}
	
	@Test
	 public void testShouldupdatePartyDetailsById() throws PartyNotFoundException{
		
		Party party=mock(Party.class);
		party.setPtyId(1);
		party.setPtyName("pikazza olivia");
		party.setPtyAddressLine1("2/33,anna nagar");
		party.setPtyAddressLine2(" ");
		party.setPtyAddressLine3(" ");
		party.setPtyTown("chennai");
		party.setPtyCounty(" ");
		party.setPtyPostCode("600028");
		party.setPtyCountry("india");
		party.setPtyLatitude((float) 52.4545);
		party.setPtyLongitude((float)-23.4545);
		party.setPtyTel("07373478346");
		party.setPtyMobile("07373478346");
		party.setPtyEmail("pikazza@gmail.com");
		party.setPtyPhoto("link");
		party.setPtyTrustScore("9");
		
		when(partyRepository.findOne(1)).thenReturn(party);
		
	}
	
	@Test
	 public void testShouldretrievePartyDetailsById() throws PartyNotFoundException{
		
		Party party=new Party();
	//	Party party=mock(Party.class);
		
		party.setPtyId(1);
		party.setPtyName("pikazza olivia");
		party.setPtyAddressLine1("2/33,anna nagar");
		party.setPtyAddressLine2(" ");
		party.setPtyAddressLine3(" ");
		party.setPtyTown("chennai");
		party.setPtyCounty(" ");
		party.setPtyPostCode("600028");
		party.setPtyCountry("india");
		party.setPtyLatitude((float) 52.4545);
		party.setPtyLongitude((float)-23.4545);
		party.setPtyTel("07373478346");
		party.setPtyMobile("07373478346");
		party.setPtyEmail("pikazza@gmail.com");
		party.setPtyPhoto("link");
		party.setPtyTrustScore("8");
		
		Party ptycircle1=mock(Party.class);
		ptycircle1.setPtyId(2);
		ptycircle1.setPtyName("XYZ");
		ptycircle1.setPtyMobile("7373478346");
		
		MyTrustCircle mtc=mock(MyTrustCircle.class);
		mtc.setParty1(party);
		mtc.setParty2(ptycircle1);
		mtc.setMtcCircle("1");

		List value=new ArrayList();

		when(partyRepository.findByptyId(1)).thenReturn(party);	
		when(myTrustCircleRepository.findByPtyId(1)).thenReturn(value);
		
		JSONObject jsob=partyServiceImpl.retrievePartyDetailsById(1);
		System.out.println("the party details  "+jsob);
		
	}

}
