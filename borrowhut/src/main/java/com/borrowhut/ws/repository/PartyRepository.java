package com.borrowhut.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.domain.Product;

public interface PartyRepository extends JpaRepository<Party, Integer> {

	//public Party findByprdId(int partyid);
	
}
