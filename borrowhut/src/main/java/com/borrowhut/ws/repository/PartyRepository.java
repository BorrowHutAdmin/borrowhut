package com.borrowhut.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.Party;

public interface PartyRepository extends JpaRepository<Party, Integer> {

	public Party findByptyId(int partyid);
	
}
