import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { AllFlightComponent } from './all-flight.component';
import { FlightService } from '../services/flight.service';
import { of } from 'rxjs';
import { Flight } from 'src/flight.model';
import { Seat } from 'src/seat.model';
import { AdminNavbarComponent } from '../admin-navbar/admin-navbar.component';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';
describe('AllFlightComponent', () => {
  let component: AllFlightComponent;
  let fixture: ComponentFixture<AllFlightComponent>;
  let flightService: FlightService;
  let debugElement: DebugElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllFlightComponent,AdminNavbarComponent ],
      imports: [HttpClientTestingModule],
      providers: [FlightService]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllFlightComponent);
    component = fixture.componentInstance;
    debugElement = fixture.debugElement;
    component.flights = [
      {
        id: 1,
        flightName: 'Test Flight',
        flightNumber:'abc1234',
        seats: [
          {seatId:1, seatClass: 'Economical', seatCapacity: 50 },
          { seatId:2,seatClass: 'Primium', seatCapacity: 20 },
          { seatId:3,seatClass: 'Business', seatCapacity: 10 }
        ]
      }
    ];
    flightService = TestBed.inject(FlightService);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should get all flights from FlightService', () => {
    const mockFlights: Flight[] = [
      {
        id: 1,
        flightName: 'Indigo',
        flightNumber: 'ab123',
        seats: []
      },
      {
        id: 2,
        flightName: 'Udaan',
        flightNumber: 'ab1234',
        seats: []
      }
    ];

    spyOn(flightService, 'getallFlight').and.returnValue(of(mockFlights));

    component.getAllFlights();

    expect(component.flights).toEqual(mockFlights);
  });

  it('should remove flight with given id', () => {
    const mockFlightId = 1;

    spyOn(window, 'confirm').and.returnValue(true);
    spyOn(flightService, 'removeFlight').and.returnValue(of());
    spyOn(component, 'getAllFlights');

    component.removeFlight(mockFlightId);

    expect(flightService.removeFlight).toHaveBeenCalledWith(mockFlightId);
  });

  it('should set flight in local storage when addSchedule is called', () => {
    const mockFlight: Flight = {
      id: 1,
      flightName: 'Indigo 1',
      flightNumber: 'ab123',
      seats: []
    };

    spyOn(localStorage, 'setItem');

    component.addSchedule(mockFlight);

    expect(localStorage.setItem).toHaveBeenCalledWith('flight', JSON.stringify(mockFlight));
  });
  it('should display the flight details', () => {
    const flightId = debugElement.query(By.css('td:nth-child(1)')).nativeElement.textContent;
    const flightName = debugElement.query(By.css('td:nth-child(2)')).nativeElement.textContent;
    const economicalSeats = debugElement.query(By.css('td:nth-child(3)')).nativeElement.textContent;
    const primiumSeats = debugElement.query(By.css('td:nth-child(4)')).nativeElement.textContent;
    const businessSeats = debugElement.query(By.css('td:nth-child(5)')).nativeElement.textContent;

    expect(flightId).toBe('Flight Id');
    expect(flightName).toBe('Flight Name');
    expect(economicalSeats).toBe('Economical Seats');
    expect(primiumSeats).toBe('Primium Seats');
    expect(businessSeats).toBe('Business Seats');
  });

  it('should navigate to the add schedule page when the "Schedule Flight" button is clicked', () => {
    const button = debugElement.query(By.css('button.btn-outline-success'));
    button.triggerEventHandler('click', null);
    fixture.detectChanges();
    fixture.debugElement.nativeElement.getAttribute('routerLink');
  });

  it('should call the removeFlight method when the "Delete Flight" button is clicked', () => {
    spyOn(component, 'removeFlight');
    const button = debugElement.query(By.css('button.btn-outline-danger'));
    button.triggerEventHandler('click', null);
    fixture.detectChanges();
    expect(component.removeFlight).toHaveBeenCalledWith(1);
  });
});
