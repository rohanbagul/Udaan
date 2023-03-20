import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Booking } from 'src/booking.model';
import { Passanger } from 'src/passanger.model';
import { Schedule } from 'src/schedule.model';
import { BookingService } from '../services/booking.service';
import { PassangerService } from '../services/passanger.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  constructor(private passangerService: PassangerService, private bookingService:BookingService, private router:Router) {
    this.psnger = new Passanger();
    
  }
 
  gender = "Select Gender"; 
  isTrue = false;
  numberOfSeat: number = 1;
  passengers: Passanger[] = [];
  passengers1: Passanger[] = [];
  psnger: Passanger;
  count: number = 0;
  isValid = false;
  isConfirm = false;
  isFrom = true
  isHeading1 = true;
  isHeading2 = false;

  schedule: Schedule = new Schedule();
  booking:Booking = new Booking()
 
  
  bookFlight() {

    this.isValid = true;
  }

  confirmBooking() {
    
    this.isConfirm = true;
    this.isFrom = false;
    this.booking.scheduleId = this.schedule.scheduleId;
    let userId = localStorage.getItem('userId');
    if(userId != null)
    {
      this.booking.userId = userId;
    }
    
  }

  confirmedBooking2()
  {
    this.booking.passengers = this.passengers;
    this.bookingService.bookFlight(this.booking).subscribe(
      data => {
        let myJson = JSON.parse(JSON.stringify(data));
        console.log(myJson);
        alert("Your flight has been booked")
        this.router.navigate(['/confirmBooking',myJson.bookingId])
      },
      error => {
        console.log(error);
      }
    )
  }


  checkCount(psnger: Passanger) {
    let psg:Passanger =  new Passanger();
    psg.passengerAge = psnger.passengerAge;
    psg.passengerCountry = psnger.passengerCountry;
    psg.passengerEmail = psnger.passengerEmail;
    psg.passengerGender = psnger.passengerGender;
    psg.passengerMobile =psnger.passengerMobile;
    psg.passengerName = psnger.passengerName
    if (this.count < this.numberOfSeat) {
      this.count = this.count + 1;
      if(this.count == this.numberOfSeat)
      {
        this.passengers.push(psg);
        this.confirmBooking();
        this.isHeading1 = false;
        this.isHeading2 = true;
        //this.isTrue = false;
      }
      else{
        this.passengers.push(psg);
        this.isTrue = true;
       
      }
    }
    else {
      console.log("error");
    }

  }

  getPassengers()
  {
    this.passengers1 = this.passengers;
  }

  


  removePassangers(pasnger: Passanger) {
   
    this.isConfirm = false;
    this.isFrom = true;
    this.count = this.count -1;
    let index = this.passengers.indexOf(pasnger);
    this.passengers.splice(index, 1);
    if (this.passengers.length == 0) {
      this.isTrue = false;
    }

   
  }

  increaseNumberOfSeat() {
    if (this.numberOfSeat < 6) {
      this.numberOfSeat = this.numberOfSeat + 1;
    }
  }

  reduceNumberOfSeat() {
    if (this.numberOfSeat > 1) {
      this.numberOfSeat = this.numberOfSeat - 1;
      //console.log("reduced");
    }
  }

  ngOnInit(): void {

    this.getbookigDetails();

  }

  
  getbookigDetails() {
    let obj = localStorage.getItem('obj');
    this.schedule = obj !== null ? JSON.parse(obj) : new Schedule();
    // console.log(this.schedule);
  }

}
