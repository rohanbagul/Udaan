import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Booking } from 'src/booking.model';
import { Passanger } from 'src/passanger.model';
import { Schedule } from 'src/schedule.model';

@Component({
  selector: 'app-confirm-booking',
  templateUrl: './confirm-booking.component.html',
  styleUrls: ['./confirm-booking.component.css']
})
export class ConfirmBookingComponent implements OnInit{

  constructor(private actv:ActivatedRoute){}
  passangers:Passanger[] = [];
 
  schedule:Schedule = new Schedule;
  bookingId:number = 0;
  


  ngOnInit(): void {
   
   this.getbookingId();
    let obj = localStorage.getItem('obj');
    this.schedule = obj !== null ? JSON.parse(obj) : new Schedule();
    console.log(this.schedule);
  }


  getbookingId()
  {
    this.actv.params.subscribe(
      data => {
        this.bookingId = data['bookingId'];
      }
    )
  }


isDisplay = false;

display()
{
  //location.reload();
  this.isDisplay = true;
}

}
