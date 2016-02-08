package com.borrowhut.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.BorrowStatus;

public interface BorrowStatusRepository extends JpaRepository<BorrowStatus, Integer>{

}
