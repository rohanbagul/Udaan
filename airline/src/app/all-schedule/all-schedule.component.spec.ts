// import { ComponentFixture, TestBed } from '@angular/core/testing';

// import { AllScheduleComponent } from './all-schedule.component';

// describe('AllScheduleComponent', () => {
//   let component: AllScheduleComponent;
//   let fixture: ComponentFixture<AllScheduleComponent>;

//   beforeEach(async () => {
//     await TestBed.configureTestingModule({
//       declarations: [ AllScheduleComponent ]
//     })
//     .compileComponents();

//     fixture = TestBed.createComponent(AllScheduleComponent);
//     component = fixture.componentInstance;
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });
// });

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { Flight } from 'src/flight.model';
import { Schedule } from 'src/schedule.model';
import { NavbarComponent } from '../navbar/navbar.component';
import { ScheduleService } from '../services/schedule.service';
import { AllScheduleComponent } from './all-schedule.component';

describe('AllScheduleComponent', () => {
  let component: AllScheduleComponent;
  let fixture: ComponentFixture<AllScheduleComponent>;
  let scheduleServiceSpy: jasmine.SpyObj<ScheduleService>;

  const schedules: Schedule[] = [
    {
      scheduleId: 1,
      arrivalTime: '12344',
      departureTime: '23434',
      arrivalDate: 'w344',
      departureDate: '2343',
      arrivalLocation: 'pune',
      leavingLocation: 'mumbai',
      flightNumber: 'a12345',
      flightName: 'Indigo',
      schduled: 'true',
      bussinessFare: 1000,
      premiumFare: 2000,
      economyFare: 3000,
      isScheduled: true,
      economySeats: 100,
      premiumSeats: 50,
      bussinessSeats: 30,
      istrue: true,
      istrue1: true,
    },
    {
      scheduleId: 2,
      arrivalTime: '1210',
      departureTime: '530',
      arrivalDate: '1204',
      departureDate: '1304',
      arrivalLocation: 'banglore',
      leavingLocation: 'delhi',
      flightNumber: 'a22345',
      flightName: 'Indigo',
      schduled: 'true',
      bussinessFare: 1000,
      premiumFare: 2000,
      economyFare: 3000,
      isScheduled: true,
      economySeats: 100,
      premiumSeats: 50,
      bussinessSeats: 30,
      istrue: true,
      istrue1: true,
    },
  ];

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('ScheduleService', [
      'getAllFlightSchdule',
      'deleteScheduleById',
    ]);

    await TestBed.configureTestingModule({
      declarations: [AllScheduleComponent, NavbarComponent],
      imports: [RouterTestingModule, HttpClientTestingModule],
      providers: [{ provide: ScheduleService, useValue: spy }],
    }).compileComponents();

    scheduleServiceSpy = TestBed.inject(
      ScheduleService
    ) as jasmine.SpyObj<ScheduleService>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllScheduleComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set schedules property with data returned from the server', () => {
    scheduleServiceSpy.getAllFlightSchdule.and.returnValue(of(schedules));

    component.ngOnInit();

    expect(scheduleServiceSpy.getAllFlightSchdule).toHaveBeenCalledWith(
      component.leave,
      component.arrive
    );
    expect(component.schedules).toEqual(schedules);
    expect(component.isTrue).toBeFalsy();
  });

  it('should set isTrue property to true if schedules length is 0', () => {
    scheduleServiceSpy.getAllFlightSchdule.and.returnValue(of([]));

    component.ngOnInit();

    expect(scheduleServiceSpy.getAllFlightSchdule).toHaveBeenCalledWith(
      component.leave,
      component.arrive
    );
    expect(component.schedules).toEqual([]);
    expect(component.isTrue).toBeTruthy();
  });

  it('should navigate to booking page with the selected schedule', () => {
    localStorage.clear();
    const schedule = schedules[0];
    spyOn(component.router, 'navigate');

    component.onClick(schedule);

    expect(component.router.navigate).toHaveBeenCalledWith(['/login']);
  });

  it('should navigate to login page if token is not available', () => {
    localStorage.removeItem('token');
    spyOn(component.router, 'navigate');

    component.onClick(schedules[0]);

    expect(component.router.navigate).toHaveBeenCalledWith(['/login']);
  });

  it('should set istrue property to true if it is currently false', () => {
    const schedule = schedules[0];
    schedule.istrue = false;

    component.showCard(schedule);

    expect(schedule.istrue).toBeTruthy();
  });
  it('should set obj in localStorage and navigate to booking when onClick is called and user is logged in', () => {
    localStorage.setItem('token', 'abc');
    const shdl:Schedule = {       scheduleId: 2,
      arrivalTime: '1210',
      departureTime: '530',
      arrivalDate: '1204',
      departureDate: '1304',
      arrivalLocation: 'banglore',
      leavingLocation: 'delhi',
      flightNumber: 'a22345',
      flightName: 'Indigo',
      schduled: 'true',
      bussinessFare: 1000,
      premiumFare: 2000,
      economyFare: 3000,
      isScheduled: true,
      economySeats: 100,
      premiumSeats: 50,
      bussinessSeats: 30,
      istrue: true,
      istrue1: true,
};
    spyOn(component.router, 'navigate');
    component.onClick(shdl);
    expect(localStorage.getItem('obj')).toEqual(JSON.stringify(shdl));
    expect(component.router.navigate).toHaveBeenCalledWith(['/booking']);
  });

  it('should toggle shd.istrue1 to false when shd.istrue1 is true in showCard1', () => {
    const shdl:Schedule = {       scheduleId: 2,
      arrivalTime: '1210',
      departureTime: '530',
      arrivalDate: '1204',
      departureDate: '1304',
      arrivalLocation: 'banglore',
      leavingLocation: 'delhi',
      flightNumber: 'a22345',
      flightName: 'Indigo',
      schduled: 'true',
      bussinessFare: 1000,
      premiumFare: 2000,
      economyFare: 3000,
      isScheduled: true,
      economySeats: 100,
      premiumSeats: 50,
      bussinessSeats: 30,
      istrue: true,
      istrue1: true,
};
    component.showCard1(shdl);
    expect(shdl.istrue1).toBe(false);
  });

 





});