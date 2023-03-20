import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Route1 } from 'src/route.model';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-all-routes',
  templateUrl: './all-routes.component.html',
  styleUrls: ['./all-routes.component.css']
})
export class AllRoutesComponent implements OnInit {

  constructor(private routeService:RouteService){

  }
  ngOnInit(): void {
    this.getAllRoutes();
  }

  allroutes:Route1[] = [];
  getAllRoutes()
  {
    this.routeService.getAllRoutes().subscribe(
      data => {
        //console.log(data);
        this.allroutes = data;
      },
      error => {
        console.log(error);
      }
    )
  }

  removeRoute(id:number)
  {
    this.routeService.removeRoute(id).subscribe(
      data => {
       alert('Route Deleted SucessFully !!!');
        this.getAllRoutes();
      },
      error => {
        console.log(error);
      }
    )
    
    
  }

}
