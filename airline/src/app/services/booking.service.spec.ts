import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Booking } from 'src/booking.model';
import { bookingDetails } from 'src/bookingData.model';
import { Passanger } from 'src/passanger.model';

import { BookingService } from './booking.service';

describe('BookingService', () => {
  let service: BookingService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ BookingService ]
    });
    service = TestBed.inject(BookingService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should book flight', () => {
    const booking: Booking = {
      userId:1,
      scheduleId:1,
      seatClass:'Economy',
      passengers:[]
      };
    service.bookFlight(booking).subscribe((response: Booking) => {
      expect(response).toEqual(booking);
    });

    const request = httpMock.expectOne('http://localhost:9191/bookingservice/createBooking');
    expect(request.request.method).toBe('POST');
    request.flush(booking);
  });

  it('should get booking by booking id', () => {
    const bookingId = 123;
    const bookingDetails: bookingDetails = {
      id: 1,
      bookingId: 1,
      bookingDate: '14032022',
      scheduleId: 2,
      flightNumber: "abc123",
      flightName: "Air India",
      arrivalDate: '15032022',
      departureDate: '15032022',
      arrivalTime: '1032323',
      departureTime: '1343535',
      arrivalLocation: 'Pune',
      departureLocation: 'Delhi',
      seatClass: 'Business',
      status: true,
      passenger: [],
      totalAmount: undefined
    };
    service.getBookingByBookingId(bookingId).subscribe((response: bookingDetails) => {
      expect(response).toEqual(bookingDetails);
    });

    const request = httpMock.expectOne(`http://localhost:9191/bookingservice/getBookingsById/${bookingId}`);
    expect(request.request.method).toBe('GET');
    request.flush(bookingDetails);
  });
});
