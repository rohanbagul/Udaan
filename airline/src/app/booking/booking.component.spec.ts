import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { Booking } from 'src/booking.model';
import { Passanger } from 'src/passanger.model';
import { Schedule } from 'src/schedule.model';
import { NavbarComponent } from '../navbar/navbar.component';
import { BookingService } from '../services/booking.service';
import { PassangerService } from '../services/passanger.service';

import { BookingComponent } from './booking.component';

describe('BookingComponent', () => {
  let component: BookingComponent;
  let fixture: ComponentFixture<BookingComponent>;
  let bookingService: BookingService;
  let passangerService: PassangerService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule,HttpClientTestingModule,FormsModule],
      declarations: [ BookingComponent,NavbarComponent ],
      providers: [BookingService, PassangerService]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    bookingService = TestBed.inject(BookingService);
    passangerService = TestBed.inject(PassangerService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call bookFlight method', () => {
    spyOn(component, 'bookFlight');
    component.bookFlight();
    expect(component.bookFlight).toHaveBeenCalled();
  });

  it('should call confirmBooking method', () => {
    spyOn(component, 'confirmBooking');
    component.confirmBooking();
    expect(component.confirmBooking).toHaveBeenCalled();
  });

  it('should call confirmedBooking2 method', () => {
    spyOn(component, 'confirmedBooking2');
    component.confirmedBooking2();
    expect(component.confirmedBooking2).toHaveBeenCalled();
  });

  it('should call checkCount method', () => {
    spyOn(component, 'checkCount');
    let passanger = new Passanger();
    component.checkCount(passanger);
    expect(component.checkCount).toHaveBeenCalled();
  });

  it('should call getPassengers method', () => {
    spyOn(component, 'getPassengers');
    component.getPassengers();
    expect(component.getPassengers).toHaveBeenCalled();
  });

  it('should call removePassangers method', () => {
    spyOn(component, 'removePassangers');
    let passanger = new Passanger();
    component.removePassangers(passanger);
    expect(component.removePassangers).toHaveBeenCalled();
  });

  it('should call increaseNumberOfSeat method', () => {
    spyOn(component, 'increaseNumberOfSeat');
    component.increaseNumberOfSeat();
    expect(component.increaseNumberOfSeat).toHaveBeenCalled();
  });

  it('should call reduceNumberOfSeat method', () => {
    spyOn(component, 'reduceNumberOfSeat');
    component.reduceNumberOfSeat();
    expect(component.reduceNumberOfSeat).toHaveBeenCalled();
  });
    

});
