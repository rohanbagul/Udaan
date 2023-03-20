import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from './services/user.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class LoginInterceptor implements HttpInterceptor {

  constructor(private userService: UserService) { }
  getToken() {
    return localStorage.getItem('token')
  }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let token = localStorage.getItem('token');

    if (token != null) {
      //console.log("header called");
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${localStorage.getItem('token')}`
        }
      })
    }


    return next.handle(request);

  }


}


