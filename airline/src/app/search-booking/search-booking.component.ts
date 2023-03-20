import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { bookingDetails } from 'src/bookingData.model';
import { BookingService } from '../services/booking.service';

@Component({
  selector: 'app-search-booking',
  templateUrl: './search-booking.component.html',
  styleUrls: ['./search-booking.component.css']
})
export class SearchBookingComponent implements OnInit {

constructor(private bookingService:BookingService, private actv:ActivatedRoute)
{

}
  bookingId=0;
  ngOnInit(): void {
    this.readPathVar();
    this.searchBookingByBookingId();
  }
 
  readPathVar()
  {
    this.actv.params.subscribe(
      data => {
        this.bookingId = data['bookingId'];
      }
    )
  }
  
  bookingdetails:bookingDetails = new bookingDetails();
  searchBookingByBookingId()
  {
     this.bookingService.getBookingByBookingId(this.bookingId).subscribe(
      data => {
        console.log(data);
        this.bookingdetails = data;
      },
      error => {
        console.log(error);
      }
     )
  }

}
