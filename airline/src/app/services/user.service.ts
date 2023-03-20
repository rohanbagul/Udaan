import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/user.model';
import { Buffer } from 'buffer/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
   massage:string="";
   token = "";
  addUser(user: User):Observable<any> {

    return this.http.post<String>('http://localhost:9191/api/v1/auth/register', user);
  }

  login(user:User): Observable<any>
  {

    return this.http.put<String>('http://localhost:9191/api/v1/auth/login',user);
  }

  getUser_1(email:string):Observable<any>{
    return this.http.get<User>('http://localhost:9191/api/v1/user/getUser/'+email)
    
  }


  changePassword(user:User): Observable<Object>{
    return this.http.put<Object>('http://localhost:9191/api/v1/auth/changePassword', user);
  }

  getUsers():Observable<any>  
  {
    return this.http.get<User[]>('http://localhost:3000/user');
  }

  removeUser(id:number):Observable<any>
  {
     return this.http.delete(`http://localhost:9191/api/v1/auth/deletebyid/${id}`);
  }


  logOut(id:number)
  {
      return this.http.put(`http://localhost:9191/api/v1/auth/logout/${id}`,null);
  }

  updateProfile(id:number,user:User)
  {
   return this.http.put(`http://localhost:9191/api/v1/auth/updateprofile/${id}`,user);
  }

  isLogedIn()
  {
    return localStorage.getItem('token') != null;
  }

  user:User = new User();
  role = "";
  checkRole()
  {
    let token = localStorage.getItem('token');
    let email = localStorage.getItem('userEmail');
    if(email != null && token != null)
    {
    this.http.get<User>('http://localhost:6667/api/v1/user/getUser/'+email).subscribe(
      data => {
        let myJson = JSON.parse(JSON.stringify(data));
          this.user = data;
          this.role = myJson.authorities[0].authority
      }
    )
    return this.role;
    }
    else
    {
      return "";
    }
  }
 
}
