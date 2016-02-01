package com.borrowhut.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borrowhut.ws.domain.PartyAuthMech;

public interface PartyAuthMechRepository extends JpaRepository<PartyAuthMech, Integer> {

}
