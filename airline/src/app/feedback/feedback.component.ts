import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { feedback } from 'src/feedback.model';
import { FeedbackService } from '../services/feedback.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit{

  constructor(private feedbackService:FeedbackService, private router:Router){}
  ngOnInit(): void {
    this.getAllFeedbacks();
  }
  feedbacks:feedback[] = [];

  getAllFeedbacks()
  {
    this.feedbackService.getAllFeedback().subscribe(
      data => {
        this.feedbacks = data;
       
      }
    )
  }

  feed:feedback = new feedback();
  submitFeedback()
  {
    this.feedbackService.addfeedback(this.feed).subscribe(
      data => {
        alert("Feedback added sucessfully");
        this.getAllFeedbacks();
      },
      error => {
        console.log(error);
      }
    )
  }

}
