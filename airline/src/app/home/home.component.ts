import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { Flight } from 'src/flight.model';
import { Route1 } from 'src/route.model';
import { FlightService } from '../services/flight.service';
import { RouteService } from '../services/route.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  leave = "Select Leaving Location";
  arrive = "Select Arrival Location";
  
  isLog = false;
  isPune = false

  now = new Date();
 
  constructor(private flightService:FlightService, private routeService:RouteService,private router:Router, private userService:UserService )
  {
    this.isLog = true
  }
ngOnInit():void{
  this.getUser();
  let source = "bangalore";
  this.getRouteBySource(source);
  this.getAllSources();
  this.getAllDestinations();
}


search()
{
   console.log(this.leave);
   console.log(this.arrive);
}

submit()
{
  if(this.leave == "Select Leaving Location")
  {
    alert("Please Sealct Leaving Location")
  }
  else if(this.arrive  == "Select Arrival Location")
  {
    alert("Please Select Arrival Location")
  }
  else if(this.arrive == this.leave)
  {
    alert("Please Select different arrival and leaving locations.")
  }
  else{
  localStorage.setItem('arrive',this.leave);
  localStorage.setItem('leave',this.arrive);
  console.log(this.arrive+this.leave)
  this.router.navigate(['/schedule'])
  }
  
}


submitFromRoute(arrive:string,leave:string)
{
  localStorage.setItem('arrive',arrive);
  localStorage.setItem('leave',leave);
  this.router.navigate(['/schedule'])
}


  flights:Flight[]=[];
  routes:Route1[] = [];
  

  getRouteBySource(source:string)
  {
   this.routeService.getRouteBySource(source).subscribe(
    data => {
      this.routes = data;
    },
    error => {
      console.log(error);
    }
   )
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

id = 0;
  getUser() {
    let token = localStorage.getItem('token');
    if (token != null) {
      let userEmail = localStorage.getItem('userEmail');
      if (userEmail != null) {
        this.userService.getUser_1(userEmail).subscribe(
          data => {
            let myJson = JSON.parse(JSON.stringify(data));
            this.id = myJson.id;
            localStorage.setItem('userId',JSON.stringify(this.id));
          },
          error => {
            console.log(error);
          }
        )
      }
    }
  }
  

}
