import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Booking } from 'src/booking.model';
import { Passanger } from 'src/passanger.model';

@Injectable({
  providedIn: 'root'
})
export class PassangerService {

  constructor(private http: HttpClient) { }

  addPassanger(passngers: Passanger[]) {
    return this.http.post<Booking>('http://localhost:3000/passanger', passngers);
  }

  removePassanger(id:number)
  {
    return this.http.delete(`http://localhost:3000/passanger/${id}`);

  }

  
  

}
