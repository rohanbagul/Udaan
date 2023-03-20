package com.wipro.supportservice.repository;

import java.util.List;
import java.util.Optional;

import com.wipro.supportservice.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

@Repository
public interface Feedbackrepository extends JpaRepository<Feedback, Integer> {

  List<Feedback> findByName(String name);
	
}
