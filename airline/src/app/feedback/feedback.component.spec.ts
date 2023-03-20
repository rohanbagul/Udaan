import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FeedbackComponent } from './feedback.component';
import { FeedbackService } from '../services/feedback.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { feedback } from 'src/feedback.model';
import { NavbarComponent } from '../navbar/navbar.component';

describe('FeedbackComponent', () => {
  let component: FeedbackComponent;
  let fixture: ComponentFixture<FeedbackComponent>;
  let feedbackService: FeedbackService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule, FormsModule],
      declarations: [FeedbackComponent,NavbarComponent],
      providers: [FeedbackService]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedbackComponent);
    component = fixture.componentInstance;
    feedbackService = TestBed.inject(FeedbackService);
    fixture.detectChanges();
  });

  it('should create FeedbackComponent', () => {
    expect(component).toBeTruthy();
  });

  it('should call getAllFeedbacks method and set feedbacks', () => {
    const feedbacks: feedback[] = [
      {
        feedbackid: 1,
        name: 'vishal',
        email: 'vishal@gmail.com',
        description: 'Good job'
      },
      {
        feedbackid: 2,
        name: 'rohan',
        email: 'rohan@gmail.com',
        description: 'Good job'
      }
    ];

    spyOn(feedbackService, 'getAllFeedback').and.returnValue(of(feedbacks));

    component.getAllFeedbacks();

    expect(feedbackService.getAllFeedback).toHaveBeenCalled();
    expect(component.feedbacks).toEqual(feedbacks);
  });

  it('should call submitFeedback method and add new feedback', () => {
    const feedback: feedback = {
      feedbackid: 1,
      name: 'vishal',
      email: 'vishal@gmail.com',
      description: 'Good job'
    };

    spyOn(feedbackService, 'addfeedback').and.returnValue(of(feedback));
    spyOn(component, 'getAllFeedbacks');

    component.feed = feedback;
    component.submitFeedback();

    expect(feedbackService.addfeedback).toHaveBeenCalledWith(feedback);
    expect(component.getAllFeedbacks).toHaveBeenCalled();
  });
});
