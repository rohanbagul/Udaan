import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from 'src/user.model';
import { UserService } from '../services/user.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) { }
  email = "";
  role = "";
user:User = new User();
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    let token = localStorage.getItem('token');
    if (token != null) {
      let str = localStorage.getItem('userEmail');
      if(str != null)
      {
       this.userService.getUser_1(str).subscribe(
        data => {
          this.user = data;
         
          if(this.user.role === "ADMIN")
          {
            return true;
          }
          else{
            this.router.navigate(['/home'])
            return false;
          }
          
        }
       )
      }

    }



     if(this.user.role === "ADMIN")
    {
      
      return true;
    }
    return false;
  }

}
