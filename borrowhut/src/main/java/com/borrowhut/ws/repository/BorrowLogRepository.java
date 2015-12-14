package com.borrowhut.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.BorrowLog;


public interface BorrowLogRepository extends JpaRepository<BorrowLog, Integer> {
	

}
