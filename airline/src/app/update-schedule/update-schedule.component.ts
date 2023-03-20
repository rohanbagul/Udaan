import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Flight } from 'src/flight.model';
import { Route1 } from 'src/route.model';
import { Schedule } from 'src/schedule.model';
import { RouteService } from '../services/route.service';
import { ScheduleService } from '../services/schedule.service';

@Component({
  selector: 'app-update-schedule',
  templateUrl: './update-schedule.component.html',
  styleUrls: ['./update-schedule.component.css']
})
export class UpdateScheduleComponent {
  constructor(private schduleService: ScheduleService, private routeService: RouteService, public router:Router) {

  }

  schedule:Schedule = new Schedule();
  ngOnInit(): void {
    let obj = localStorage.getItem('schedule');
    this.schedule = obj !== null ? JSON.parse(obj) : new Schedule();
    this.schduleFlight.flightName = this.schedule.flightName;
    this.schduleFlight.flightNumber = this.schedule.flightNumber;
    this.getAllSources();
    this.getAllDestinations();
  }

  schduleFlight: Schedule = new Schedule()
  
  leave = "Select Leaving Location";
  arrive = "Select Arrival Location";

  submit() {
    this.schduleFlight.arrivalLocation = this.arrive;
    this.schduleFlight.leavingLocation = this.leave;
   
    this.schduleService.updateSchedule(this.schduleFlight,this.schedule.scheduleId).subscribe(
      data => {  
        alert("Flight Updated sucessfully !!!");
        this.router.navigate(['/allschedule'])
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
