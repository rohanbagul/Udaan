import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { bookingDetails } from 'src/bookingData.model';
import { BookingService } from '../services/booking.service';

@Component({
  selector: 'app-mybooking',
  templateUrl: './mybooking.component.html',
  styleUrls: ['./mybooking.component.css'],
})
export class MybookingComponent implements OnInit {
  constructor(
    private actv: ActivatedRoute,
    private bookinService: BookingService
  ) {}
  ngOnInit(): void {
    this.readPathVar();
    this.getmybooking();
  }
  userId: number = 0;
  readPathVar() {
    this.actv.params.subscribe((data) => {
      this.userId = data['id'];
      console.log(this.userId);
    });
  }
  bookingdetals: bookingDetails[] = [];
  isDataFound = false;
  getmybooking() {
    this.bookinService.getmybooking(this.userId).subscribe(
      (data) => {
        this.bookingdetals = data;
        if (this.bookingdetals.length == 0) {
          this.isDataFound = true;
        }
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
