package com.example.MaupinAirlineTicketSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.MaupinAirlineTicketSystem.entity.ChatCategory;

@Repository
public interface ChatCategoryRepository extends JpaRepository<ChatCategory, Long>{

	List<ChatCategory> 
    findByActiveTrueOrderByDisplayOrderAsc();
}
