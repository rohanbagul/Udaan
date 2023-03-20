import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight } from 'src/flight.model';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient) { }

  getallFlight():Observable<any>
  {
    return this.http.get<Flight[]>('http://localhost:9191/flightservice/getFlight');
  }
 
 addFlight(flight:Flight)
 {
  return this.http.post<Flight>('http://localhost:9191/flightservice/addFlight',flight);
 }
 

 removeFlight(id:number)
 {
    return this.http.delete(`http://localhost:9191/flightservice/deleteFlight/${id}`)
 }


}
