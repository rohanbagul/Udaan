import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking } from 'src/booking.model';
import { bookingDetails } from 'src/bookingData.model';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http:HttpClient) { }

  bookFlight(booking:Booking)
  {
   
     return this.http.post<Booking>("http://localhost:9191/bookingservice/createBooking",booking);
  }

  getBookingByBookingId(id:number):Observable<any>
  {
    return this.http.get<bookingDetails>(`http://localhost:9191/bookingservice/getBookingsById/${id}`);
  }

  getmybooking(id:number)
  {
    return this.http.get<bookingDetails[]>(`http://localhost:9191/api/v1/user/getMyBooking/${id}`)
  }
}
