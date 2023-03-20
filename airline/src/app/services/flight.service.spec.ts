import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Flight } from 'src/flight.model';
import { FlightService } from './flight.service';

describe('FlightService', () => {
  let service: FlightService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [FlightService]
    });
    service = TestBed.inject(FlightService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return all flights', () => {
    const mockFlights: Flight[] = [
      {     id:1,
        flightNumber:'abc1234',
        flightName:'Vistara',
        seats:[]
    },
      { id:2,
        flightNumber:'xyz213',
        flightName:'Air India',
        seats:[] }
    ];

    service.getallFlight().subscribe((flights: Flight[]) => {
      expect(flights.length).toBe(2);
      expect(flights).toEqual(mockFlights);
    });

    const request = httpMock.expectOne('http://localhost:9191/flightservice/getFlight');
    expect(request.request.method).toBe('GET');
    request.flush(mockFlights);
  });

  it('should add a new flight', () => {
    const newFlight: Flight = { id:1,
      flightNumber:'abc1234',
      flightName:'Vistara',
      seats:[] };

    service.addFlight(newFlight).subscribe((flight: Flight) => {
      expect(flight).toEqual(newFlight);
    });

    const request = httpMock.expectOne('http://localhost:9191/flightservice/addFlight');
    expect(request.request.method).toBe('POST');
    request.flush(newFlight);
  });

  it('should remove a flight', () => {
    const idToRemove = 2;

    service.removeFlight(idToRemove).subscribe();

    const request = httpMock.expectOne(`http://localhost:9191/flightservice/deleteFlight/${idToRemove}`);
    expect(request.request.method).toBe('DELETE');
  });
});
