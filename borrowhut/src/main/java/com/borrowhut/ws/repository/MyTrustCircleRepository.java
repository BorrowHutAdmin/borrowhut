package com.borrowhut.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.MyTrustCircle;
import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.domain.UiCard;

public interface MyTrustCircleRepository extends JpaRepository<MyTrustCircle, Integer> {
	
	public List<MyTrustCircle> findByPtyId(int ptyId);

}
