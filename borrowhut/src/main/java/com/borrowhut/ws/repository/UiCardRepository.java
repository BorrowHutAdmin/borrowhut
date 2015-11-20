package com.borrowhut.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.borrowhut.ws.domain.UiCard;

public interface UiCardRepository extends JpaRepository<UiCard, String>{
	

public List<UiCard> findByUserSpecific(String param);
}
