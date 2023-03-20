import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { ConfirmBookingComponent } from './confirm-booking.component';

describe('ConfirmBookingComponent', () => {
  let component: ConfirmBookingComponent;
  let fixture: ComponentFixture<ConfirmBookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmBookingComponent,NavbarComponent ],imports:[HttpClientTestingModule,FormsModule],
      providers: [
        { provide: ActivatedRoute, useValue: {
            params: {
              subscribe: (fn: (value: any) => void) => fn({
                bookingId: 123 // sample bookingId value
              })
            }
          }
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should set bookingId correctly from route parameters', () => {
    expect(component.bookingId).toBe(123);
  });

  it('should initialize schedule from local storage', () => {
    localStorage.setItem('obj', JSON.stringify({ /* sample Schedule object */ }));
    component.ngOnInit();
    expect(component.schedule).toBeDefined();
  });

  it('should set isDisplay to true when display() is called', () => {
    component.display();
    expect(component.isDisplay).toBeTrue();
  });
});
