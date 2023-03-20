import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { FeedbackService } from './feedback.service';
import { feedback } from 'src/feedback.model';

describe('FeedbackService', () => {
  let service: FeedbackService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [FeedbackService]
    });
    service = TestBed.inject(FeedbackService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('sendFeedback', () => {
    it('should send feedback successfully', () => {
      const mockFeedback:feedback = {
        feedbackid:1,
        name:"rohan bagul",
        email:'rohan@gmail.com',
        description:'this content was awesome!!'
          };
      const mockResponse:feedback = {
        feedbackid:1,
        name:"rohan bagul",
        email:'rohan@gmail.com',
        description:'this content was awesome!!'
          };
      service.addfeedback(mockFeedback).subscribe((response) => {
        expect(response).toEqual(mockResponse);
      });
      const req = httpMock.expectOne('http://localhost:9191/udaan/feedback/addfeedback');
      expect(req.request.method).toBe('POST');
      req.flush(mockResponse);
    });

    
  });
});
