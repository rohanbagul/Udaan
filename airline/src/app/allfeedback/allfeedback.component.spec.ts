import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { FeedbackService } from '../services/feedback.service';
import { feedback } from 'src/feedback.model';
import { AllfeedbackComponent } from './allfeedback.component';
import { AdminNavbarComponent } from '../admin-navbar/admin-navbar.component';


describe('AllfeedbackComponent', () => {
  let component: AllfeedbackComponent;
  let fixture: ComponentFixture<AllfeedbackComponent>;
  let feedbackService: FeedbackService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllfeedbackComponent,AdminNavbarComponent ],
      imports: [ HttpClientTestingModule ],
      providers: [ FeedbackService ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllfeedbackComponent);
    component = fixture.componentInstance;
    feedbackService = TestBed.inject(FeedbackService);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch all feedbacks on component initialization', () => {
    const feedbacks: feedback[] = [
      { feedbackid: 1, name: 'abc', email: 'fedbakc@gmail.com',description:'hi' },
      { feedbackid: 2, name: 'xyz', email: 'xyg@gmail.com',description:'hi' },
      { feedbackid: 3, name: 'cdg', email: 'dsfd@gmail.com',description:'hi' },
    ];
    spyOn(feedbackService, 'getAllFeedback').and.returnValue(of(feedbacks));
    component.ngOnInit();
    expect(component.feedbacks).toEqual(feedbacks);
  });

  it('should remove a feedback', () => {
    const feedbacks: feedback[] = [
      { feedbackid: 1, name: 'abc', email: 'fedbakc@gmail.com',description:'hi' },
      { feedbackid: 2, name: 'xyz', email: 'xyg@gmail.com',description:'hi' },
      { feedbackid: 3, name: 'cdg', email: 'dsfd@gmail.com',description:'hi' },
    ];
    const deletedFeedbackId = 2;
    spyOn(feedbackService, 'getAllFeedback').and.returnValue(of(feedbacks));
    spyOn(feedbackService, 'removefeedback').and.returnValue(of());
    spyOn(window, 'alert');
    component.removeFeedback(deletedFeedbackId);
    expect(feedbackService.removefeedback).toHaveBeenCalledWith(deletedFeedbackId);
  });

});
