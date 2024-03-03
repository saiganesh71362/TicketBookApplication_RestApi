package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.TicketDetails;
@Repository
public interface TicketRepository extends JpaRepository<TicketDetails, Integer>{

}
