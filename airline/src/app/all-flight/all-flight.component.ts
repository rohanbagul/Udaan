import { Component, OnInit } from '@angular/core';
import { Flight } from 'src/flight.model';
import { Seat } from 'src/seat.model';
import { FlightService } from '../services/flight.service';

@Component({
  selector: 'app-all-flight',
  templateUrl: './all-flight.component.html',
  styleUrls: ['./all-flight.component.css']
})
export class AllFlightComponent implements OnInit {

  constructor(private flightService:FlightService)
  {

  }
  ngOnInit(): void {
    this.getAllFlights();
    
  }

  addSchedule(flight:Flight)
  {
     localStorage.setItem('flight',JSON.stringify(flight));
  }

  seats:Seat[] = [];

  flights:Flight[] = [];


  getAllFlights()
  {
     this.flightService.getallFlight().subscribe(
      data => {
        console.log(data);
        this.flights = data;
      },
      error => {
        console.log(error);
      }
     )
  }

  removeFlight(id:number)
  {
    this.flightService.removeFlight(id).subscribe(
      data => { 
        alert("Flight deleted Sucessfully");
        this.getAllFlights();
      }
    )
  }

}
