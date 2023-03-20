import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { of } from 'rxjs';
import { Schedule } from 'src/schedule.model';
import { NavbarComponent } from '../navbar/navbar.component';

import { ScheduleComponent } from './schedule.component';

describe('ScheduleComponent', () => {
  let component: ScheduleComponent;
  let fixture: ComponentFixture<ScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleComponent,NavbarComponent ],imports:[HttpClientTestingModule,FormsModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call getAllSchduleFlights method on initialization', () => {
    const fixture = TestBed.createComponent(ScheduleComponent);
    const component = fixture.componentInstance;
    spyOn(component, 'getAllSchduleFlights');
    component.ngOnInit();
    expect(component.getAllSchduleFlights).toHaveBeenCalled();
  });

  it('should populate schedules array with data on successful API call', () => {
    const fixture = TestBed.createComponent(ScheduleComponent);
    const component = fixture.componentInstance;
    const mockSchedules:Schedule[] = [{
      scheduleId:1,
    arrivalTime:'12032022',
    departureTime:'14032022',
    arrivalDate:'14032022',
    departureDate:'15032022',
    arrivalLocation:'Pune',
    leavingLocation:'Banglore',
    flightNumber:'abc123',
    flightName:'Indigo',
    schduled:true,
    bussinessFare:2000,
    premiumFare:3000,
    economyFare:4000,
    isScheduled:true,
    economySeats:100,
    premiumSeats:50,
    bussinessSeats:30,
    istrue:true,
    istrue1:true,

    }];
    spyOn(component.schduleService, 'getFlightSchdule').and.returnValue(of(mockSchedules));
    component.getAllSchduleFlights();
    expect(component.schedules).toEqual(mockSchedules);
  });

  it('should set isTrue to true when schedules array is empty', () => {
    const fixture = TestBed.createComponent(ScheduleComponent);
    const component = fixture.componentInstance;
    spyOn(component.schduleService, 'getFlightSchdule').and.returnValue(of([]));
    component.getAllSchduleFlights();
    expect(component.isTrue).toBeTrue();
  });

  it('should toggle istrue property value when showCard method is called', () => {
    const fixture = TestBed.createComponent(ScheduleComponent);
    const component = fixture.componentInstance;
    const mockSchedule:Schedule = {
      scheduleId:1,
    arrivalTime:'12032022',
    departureTime:'14032022',
    arrivalDate:'14032022',
    departureDate:'15032022',
    arrivalLocation:'Pune',
    leavingLocation:'Banglore',
    flightNumber:'abc123',
    flightName:'Indigo',
    schduled:true,
    bussinessFare:2000,
    premiumFare:3000,
    economyFare:4000,
    isScheduled:true,
    economySeats:100,
    premiumSeats:50,
    bussinessSeats:30,
    istrue:true,
    istrue1:true,

    };
    component.showCard(mockSchedule);
    expect(mockSchedule.istrue).toBeFalse();
  });
  
  it('should navigate to booking page and save selected schedule in localStorage when onClick method is called', () => {
    const fixture = TestBed.createComponent(ScheduleComponent);
    const component = fixture.componentInstance;
    const mockSchedule:Schedule = {
      scheduleId:1,
    arrivalTime:'12032022',
    departureTime:'14032022',
    arrivalDate:'14032022',
    departureDate:'15032022',
    arrivalLocation:'Pune',
    leavingLocation:'Banglore',
    flightNumber:'abc123',
    flightName:'Indigo',
    schduled:true,
    bussinessFare:2000,
    premiumFare:3000,
    economyFare:4000,
    isScheduled:true,
    economySeats:100,
    premiumSeats:50,
    bussinessSeats:30,
    istrue:true,
    istrue1:true,

    };
    spyOn(component.router, 'navigate');
    component.onClick(mockSchedule);
    expect(localStorage.getItem('obj')).toEqual(JSON.stringify(mockSchedule));
    expect(component.router.navigate).toHaveBeenCalledWith(['/booking']);
  });
  
  
  
});
