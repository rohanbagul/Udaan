import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Route1 } from 'src/route.model';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-add-route',
  templateUrl: './add-route.component.html',
  styleUrls: ['./add-route.component.css']
})
export class AddRouteComponent {


  constructor(private routeService: RouteService, private router:Router) {

  }

  route: Route1 = new Route1();

  addRoute() {
    console.log(this.route);
    this.routeService.addRoute(this.route).subscribe(
      data => {
        console.log(data);
        alert("Route Added SucessFully");
        this.router.navigate(['/allroutes']);
      },
      error => {
        alert("Route already exist!");
      }
    )
  }

}
