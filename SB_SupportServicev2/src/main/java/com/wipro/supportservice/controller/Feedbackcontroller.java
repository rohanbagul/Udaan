package com.wipro.supportservice.controller;

import java.util.List;

import com.wipro.supportservice.dto.Feedbackdto;
import com.wipro.supportservice.entities.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.supportservice.service.Feedbackservice;


@RestController
@RequestMapping("/udaan/feedback")
public class Feedbackcontroller {
	
	@Autowired
	private Feedbackservice feedbackservice;
	
	@PostMapping("/addfeedback")
	public ResponseEntity<Feedback> addfeedback(@RequestBody Feedbackdto feedbackdto) {
		Feedback feedback = feedbackservice.addFeedback(feedbackdto);

		return new ResponseEntity<>(feedback, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteFeedback/{feedbackid}")
	public ResponseEntity<?> deletefeedback(@PathVariable Integer feedbackid){
		feedbackservice.deleteFeedbackById(feedbackid);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/getallfeedback")
	public ResponseEntity<List<Feedback>> getallfeedback()
	{
		return new ResponseEntity<>(feedbackservice.getAllFeedback(), HttpStatus.OK);
	}

	@GetMapping("/getfeedbackByid/{feedbackid}")
	public ResponseEntity<Feedback> getfeedbackByid(@PathVariable Integer feedbackid){
		
		return ResponseEntity.ok(feedbackservice.getFeedbackById(feedbackid));
	}
	
	
	@GetMapping("/getFeedbackByUserName/{name}")
	public ResponseEntity<List<Feedback>> getFeedbackByUserName(@PathVariable String name) {
		return new ResponseEntity<>(this.feedbackservice.getFeedbackByName(name),HttpStatus.OK);
		
	}
}
