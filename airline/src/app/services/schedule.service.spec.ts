import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Schedule } from 'src/schedule.model';
import { ScheduleService } from './schedule.service';

describe('ScheduleService', () => {
  let service: ScheduleService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ScheduleService]
    });
    service = TestBed.inject(ScheduleService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should add flight schedule successfully', () => {
    const schedule: Schedule = { 
      scheduleId:1,
      arrivalTime:'12032022',
      departureTime:'3432434',
      arrivalDate:'2345544',
      departureDate:'3664566',
      arrivalLocation:'Baglore',
      leavingLocation:'Pune',
      flightNumber:'abc1235',
      flightName:'CISNT',
      schduled:true,
      bussinessFare:1000,
      premiumFare:2000,
      economyFare:3000,
      isScheduled:true,
      economySeats:100,
      premiumSeats:50,
      bussinessSeats:30,
      istrue:true,
      istrue1:true,

     };
    service.addFlightSchedule(schedule).subscribe(response => {
      expect(response).toEqual(schedule);
    });

    const req = httpMock.expectOne('http://localhost:9191/scheduleService/addSchedule');
    expect(req.request.method).toBe('POST');
    req.flush(schedule);
  });

  it('should get flight schedule successfully by arrive and leave location', () => {
    const leave = 'A';
    const arrive = 'B';
    const schedules: Schedule[] = [{ 
      scheduleId:1,
      arrivalTime:'12032022',
      departureTime:'3432434',
      arrivalDate:'2345544',
      departureDate:'3664566',
      arrivalLocation:'Baglore',
      leavingLocation:'Pune',
      flightNumber:'abc1235',
      flightName:'CISNT',
      schduled:true,
      bussinessFare:1000,
      premiumFare:2000,
      economyFare:3000,
      isScheduled:true,
      economySeats:100,
      premiumSeats:50,
      bussinessSeats:30,
      istrue:true,
      istrue1:true,
  }];
    service.getFlightSchdule(arrive, leave).subscribe(response => {
      expect(response).toEqual(schedules);
    });

    const req = httpMock.expectOne(`http://localhost:9191/scheduleService/getSchedByLeaveAndArrLoc/${leave}/${arrive}`);
    expect(req.request.method).toBe('GET');
    req.flush(schedules);
  });

  it('should get all flight schedules successfully', () => {
    const schedules: Schedule[] = [{       scheduleId:1,
      arrivalTime:'12032022',
      departureTime:'3432434',
      arrivalDate:'2345544',
      departureDate:'3664566',
      arrivalLocation:'Baglore',
      leavingLocation:'Pune',
      flightNumber:'abc1235',
      flightName:'CISNT',
      schduled:true,
      bussinessFare:1000,
      premiumFare:2000,
      economyFare:3000,
      isScheduled:true,
      economySeats:100,
      premiumSeats:50,
      bussinessSeats:30,
      istrue:true,
      istrue1:true,
}];
    service.getAllFlightSchdule('', '').subscribe(response => {
      expect(response).toEqual(schedules);
    });

    const req = httpMock.expectOne('http://localhost:9191/scheduleService/getSchedule');
    expect(req.request.method).toBe('GET');
    req.flush(schedules);
  });

  

  it('should update flight schedule successfully by id', () => {
    const id = 1;
    const schedule: Schedule ={
      scheduleId:1,
      arrivalTime:'12032022',
      departureTime:'3432434',
      arrivalDate:'2345544',
      departureDate:'3664566',
      arrivalLocation:'Baglore',
      leavingLocation:'Pune',
      flightNumber:'abc1235',
      flightName:'CISNT',
      schduled:true,
      bussinessFare:1000,
      premiumFare:2000,
      economyFare:3000,
      isScheduled:true,
      economySeats:100,
      premiumSeats:50,
      bussinessSeats:30,
      istrue:true,
      istrue1:true,

    };
    service.updateSchedule(schedule, id).subscribe(response => {
      expect(response).toEqual(schedule);
    });

    const req = httpMock.expectOne(`http://localhost:9191/scheduleService/updateSchedule/${id}`);
    expect(req.request.method).toBe('PUT');
    req.flush(schedule);
  });
});
