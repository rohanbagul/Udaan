import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { Flight } from 'src/flight.model';
import { Route1 } from 'src/route.model';
import { Schedule } from 'src/schedule.model';
import { RouteService } from '../services/route.service';
import { ScheduleService } from '../services/schedule.service';


@Component({
  selector: 'app-add-schedule',
  templateUrl: './add-schedule.component.html',
  styleUrls: ['./add-schedule.component.css']
})
export class AddScheduleComponent implements OnInit{

  constructor(private schduleService: ScheduleService, private routeService: RouteService,private router:Router) {

  }

  flight1:Flight = new Flight;
  ngOnInit(): void {
    let obj = localStorage.getItem('flight');
    this.flight1 = obj !== null ? JSON.parse(obj) : new Schedule();
    this.schduleFlight.flightName = this.flight1.flightName;
    this.schduleFlight.flightNumber = this.flight1.flightNumber;
    this.getAllSources();
    this.getAllDestinations();
  }

  schduleFlight: Schedule = new Schedule()
  
  leave = "Select Leaving Location";
  arrive = "Select Arrival Location";

  submit() {
    this.schduleFlight.arrivalLocation = this.arrive;
    this.schduleFlight.leavingLocation = this.leave;
    console.log(this.schduleFlight);
    this.schduleService.addFlightSchedule(this.schduleFlight).subscribe(
      data => {
        let myJson = JSON.parse(JSON.stringify(data));
        alert("Flight schedule added sucessfully !!!");
        this.router.navigate(['/allschedule']);
        
      },
      error => {
        console.log(error);
      }

    );
  }
  allroutes:Route1[] = [];
  allSources:string[] = [];
  getAllSources()
  {
    this.routeService.getAllRoutes().subscribe(
      data => {
        this.allroutes = data;
        for(let i = 0; i < this.allroutes.length; i++)
        {
          if (!this.allSources.includes(this.allroutes[i].source)) {
          this.allSources.push(this.allroutes[i].source);
          }
          
        }
      },
     error => {
      console.log(error);
     }
      
    )
  }



  alldestinations:string[] = [];
  getAllDestinations()
  {
    this.routeService.getAllRoutes().subscribe(
      data => {
        this.allroutes = data;
        for(let i = 0; i < this.allroutes.length; i++)
        {
          if (!this.alldestinations.includes(this.allroutes[i].destination)) {
          this.alldestinations.push(this.allroutes[i].destination);
          }
          
        }  
      },
     error => {
      console.log(error);
     }
      
    )
  }

}
