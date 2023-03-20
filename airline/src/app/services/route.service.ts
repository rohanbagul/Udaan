import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Route1 } from 'src/route.model';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private http:HttpClient) { }
  route:Route1 = new Route1();

  addRoute(route:Route1):Observable<any>
  {
    console.log(route);
    return this.http.post<any>('http://localhost:9191/api/v2/addRoute',route);
  }

  getRouteBySource(source:string)
  {
    return this.http.get<Route1[]>(`http://localhost:9191/api/v2/getRouteBySource/${source}`);
  }

  getAllRoutes()
  {
    return this.http.get<Route1[]>('http://localhost:9191/api/v2/getRoute');
  }

  removeRoute(id:number)
  {
    return this.http.delete(`http://localhost:9191/api/v2/deleteroute/${id}`);
  }

}
