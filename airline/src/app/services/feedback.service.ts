import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { feedback } from 'src/feedback.model';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private http:HttpClient) { }

  getAllFeedback()
  {
    return this.http.get<feedback[]>('http://localhost:9191/udaan/feedback/getallfeedback');
  }

  addfeedback(feed:feedback)
  {
    return this.http.post<feedback>('http://localhost:9191/udaan/feedback/addfeedback',feed);
  }

  removefeedback(id:number)
  {
      return this.http.delete(`http://localhost:9191/udaan/feedback/deleteFeedback/${id}`);
  }
}
