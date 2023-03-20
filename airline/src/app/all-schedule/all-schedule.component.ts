import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Flight } from 'src/flight.model';
import { Schedule } from 'src/schedule.model';
import { ScheduleService } from '../services/schedule.service';

@Component({
  selector: 'app-all-schedule',
  templateUrl: './all-schedule.component.html',
  styleUrls: ['./all-schedule.component.css']
})
export class AllScheduleComponent {

  constructor(private schduleService:ScheduleService, public router:Router){

  }
  
  schedules:Schedule[] = [];
  shd:Schedule = new Schedule();
  flight:Flight = new Flight;
  arrive = "";
  leave = "";
  ngOnInit()
  {
    let arriveLocal = localStorage.getItem('arrive');
    let leaveLocal = localStorage.getItem('leave');
    if(arriveLocal != null )
    {
      this.arrive = arriveLocal;
    }
    
    if(leaveLocal != null)
    {
      this.leave = leaveLocal;
    }
  
    this.getAllSchduleFlights();
    
  }
  
  now = new Date();
  fare = 4500;
  id = 0;
  istrue = false
  istrue1 = false
  
  
  showCard(shd:Schedule)
  {
      if(shd.istrue == false)
    {
      shd.istrue = true;
    }
    else if(shd.istrue == true)
    {
      shd.istrue = false;
    }
    else{
      shd.istrue = false;
    }
  }
  
  showCard1(shd:Schedule)
  {
    if(shd.istrue1 == false)
    {
      shd.istrue1 = true;
    }
    else if(shd.istrue1 == true)
    {
      shd.istrue1 = false;
    }
    else{
      shd.istrue1 = false;
    }
  }
  
  
  shdl:Schedule = new Schedule();
  onClick(shdl:Schedule)
  {
    let str = localStorage.getItem('token');
    // console.log(str);
    if(str != null)
    {
    localStorage.setItem('obj',JSON.stringify(shdl));
    this.router.navigate(['/booking'])
    }
    else
    {
      alert('login to book flight');
      this.router.navigate(['/login']);
  
    }
     
  }
  
  isTrue = false;
  
  getAllSchduleFlights()
  {
    
    this.schduleService.getAllFlightSchdule(this.leave,this.arrive).subscribe(
      data => {
          console.log(data)
          this.schedules = data;
          if(this.schedules.length == 0)
          {
            this.isTrue = true;
          }
      },
      error => {
        console.log(error);
      }
      
    )
  }


  deleteSchedule(id:number)
  {
    console.log(id);
   
    this.schduleService.deleteScheduleById(id).subscribe(
      data => {
          alert("Schedule deleted Sucessfully");
          this.getAllSchduleFlights();
      },
      error => {
        
      }
    )
  }


  updateSchedule(schedule:Schedule)
  {
     localStorage.setItem('schedule', JSON.stringify(schedule));
     this.router.navigate(['/updateschedule']);
  }

}
