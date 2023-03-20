import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Flight } from 'src/flight.model';
import { Seat } from 'src/seat.model';
import { FlightService } from '../services/flight.service';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.css']
})
export class AddFlightComponent {
  constructor(private flightService: FlightService,private router:Router) {

  }

  flight: Flight = new Flight;

  seatEconomy:Seat = new Seat();
  seatPremium:Seat = new Seat();
  seatBusiness:Seat = new Seat();

  addFlight()
  {
    if(this.addForm.valid)
    {
    this.seatEconomy.seatClass = "Economy";
    this.seatPremium.seatClass = "Premium";
    this.seatBusiness.seatClass = "Bussiness";
    this.flight.seats.push(this.seatEconomy);
    this.flight.seats.push(this.seatPremium);
    this.flight.seats.push(this.seatBusiness);
    this.flightService.addFlight(this.flight).subscribe(
      data => {
        console.log(data);
        alert("Flight added sucessfully!!!");
      this.router.navigate(['/allFlight'])
      },
      error => {
        console.log(error);
      }
    )
    }
    else{
      alert("All fields are required.")
    }
  }

  myFormValidation() {

    this.addForm.controls['flightName'].setValidators([Validators.required]);
    this.addForm.controls['flightNumber'].setValidators([Validators.required]);
    this.addForm.controls['EconomySeat'].setValidators([Validators.required]);
    this.addForm.controls['primiumSeat'].setValidators([Validators.required]);
    this.addForm.controls['bussinessSeat'].setValidators([Validators.required]);
   
  }

  addForm = new FormGroup(
    {
      flightName: new FormControl(),
      flightNumber: new FormControl(),
      EconomySeat: new FormControl(),
      primiumSeat: new FormControl(),
      bussinessSeat:new FormControl(),

    }
  )
}
