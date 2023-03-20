import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Schedule } from 'src/schedule.model';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  constructor(private http:HttpClient) { }
  
  schedule:Schedule[] = [];
  addFlightSchedule(schedule:Schedule):Observable<any>
  {
    return this.http.post<Schedule>('http://localhost:9191/scheduleService/addSchedule',schedule);
  }

  getFlightSchdule(arrive:string,leave:string):Observable<any>
  {
    console.log(leave+' '+arrive);
    return this.http.get<Schedule[]>(`http://localhost:9191/scheduleService/getSchedByLeaveAndArrLoc/${leave}/${arrive}`);
  }

  getAllFlightSchdule(arrive:string,leave:string):Observable<any>
  {
    return this.http.get<Schedule[]>('http://localhost:9191/scheduleService/getSchedule');
  }

  deleteScheduleById(id:number)
  {
      return this.http.delete(`http://localhost:9191/scheduleService/deleteSchedule/${id}`)
  }

  updateSchedule(schedule:Schedule, id:number):Observable<any>
  {
    return this.http.put<Schedule>(`http://localhost:9191/scheduleService/updateSchedule/${id}`,schedule);
  }

  
}
