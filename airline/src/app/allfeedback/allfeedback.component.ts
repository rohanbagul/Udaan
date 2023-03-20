import { Component, OnInit } from '@angular/core';
import { feedback } from 'src/feedback.model';
import { FeedbackService } from '../services/feedback.service';

@Component({
  selector: 'app-allfeedback',
  templateUrl: './allfeedback.component.html',
  styleUrls: ['./allfeedback.component.css']
})
export class AllfeedbackComponent implements OnInit{
constructor(private feedbackservice:FeedbackService){}
  ngOnInit(): void {
   this.getAllFeedbacks();
  }

  feedbacks:feedback[] = [];
  

  getAllFeedbacks()
  {
    this.feedbackservice.getAllFeedback().subscribe(
      data => {
        this.feedbacks = data;
       
      }
    )
  }

  removeFeedback(feedbackId:number)
  {
      this.feedbackservice.removefeedback(feedbackId).subscribe(
          data => {
            alert("Feedback deleted sucessfully !!!");
            this.getAllFeedbacks();
          }, 
          error=>{
            console.log(error);
          }
      )
  }
}
