import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { PassangerService } from './passanger.service';
import { Booking } from 'src/booking.model';
import { Passanger } from 'src/passanger.model';

describe('PassangerService', () => {
  let service: PassangerService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PassangerService]
    });
    service = TestBed.inject(PassangerService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should add passangers', () => {
    const passangers: Passanger[] = [
      {     pid:1,
        passengerName:'rohan bagul',
        passengerAge:23,
        passengerGender:'male',
        passengerMobile:'9172591403',
        passengerEmail:'rohan@gmail.com',
        passengerCountry:"India"
    }    ];
    const booking: Booking = {  userId:1,
         scheduleId:1,
      seatClass:"Economy",
      passengers:[]
   };
    service.addPassanger(passangers).subscribe(data => {
      expect(data).toEqual(booking);
    });
    const req = httpMock.expectOne('http://localhost:3000/passanger');
    expect(req.request.method).toEqual('POST');
    req.flush(booking);
  });

  it('should remove a passanger', () => {
    const id = 1;
    service.removePassanger(id).subscribe(data => {
      expect(data).toBeTruthy();
    });
    const req = httpMock.expectOne(`http://localhost:3000/passanger/${id}`);
    expect(req.request.method).toEqual('DELETE');
    req.flush({});
  });
});
