package com.wipro.supportservice.service;

import java.util.List;
import java.util.Optional;

import com.wipro.supportservice.dto.Feedbackdto;
import com.wipro.supportservice.entities.Feedback;
import com.wipro.supportservice.exception.CustomException;
import com.wipro.supportservice.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.supportservice.repository.Feedbackrepository;


@Service
public class Feedbackservice {

	@Autowired
	Feedbackrepository feedbackrepo;

	public void deleteFeedbackById(int feedbackid) {
		
		Feedback feedback = feedbackrepo.findById(feedbackid).orElseThrow(()-> new NotFoundException("Data not found"));
		if(feedback != null) {
		feedbackrepo.deleteById(feedbackid);
		}
	}
	

	public Feedback addFeedback(Feedbackdto feedbackdto) {
		Feedback feedback = new Feedback();
		
		feedback.setName(feedbackdto.getName());
		feedback.setEmail(feedbackdto.getEmail());
		feedback.setDescription(feedbackdto.getDescription());
		
		return feedbackrepo.save(feedback);
	}
	public List<Feedback> getAllFeedback(){
		return feedbackrepo.findAll();
	}

	public Feedback getFeedbackById(int feedbackid) {
		Feedback feedback = feedbackrepo.findById(feedbackid).orElseThrow(() -> new NotFoundException("Data not found"));
		return feedback;
	}


	public List<Feedback> getFeedbackByName(String name) {

		return feedbackrepo.findByName(name);
	}
	
}
