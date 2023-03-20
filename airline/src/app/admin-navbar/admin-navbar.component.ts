import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent implements OnInit {

  constructor(public router: Router, private userService: UserService) {

  }

  ngOnInit(): void {
    this.getUser();
    this.checkDisplayLoginStatus();
   // this.checkAdmin();
    this.checkLogin();
   

  }

  user: User = new User();
  isLogin = false;
  displayLoginStatus = "LogIn";
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
          },
          error => {
            console.log(error);
          }
        )
      }
    }
  }


 
  checkDisplayLoginStatus()
  {
    let token = localStorage.getItem('token');
    if(token != null)
    {
      this.displayLoginStatus = "LogOut";
    } 

  }


  logOut()
  {
    localStorage.clear();
    if(this.displayLoginStatus == "LogIn")
    {
      this.router.navigate(['/login']);
    }
    else
    {
    this.userService.logOut(this.id).subscribe(
      data => {
        this.displayLoginStatus = "LogIn";
        this.router.navigate(['/login']);
      },
      error =>
      {
        console.log(error);
      }
    )
    }
  }

  removeAccount()
  {
    
    this.userService.removeUser(this.id).subscribe(
      data => {
        alert("Your accont deleted sucessfully!");
        localStorage.clear();
        this.router.navigate(['/login']);
      },
      error => {
        console.log(error);
      }
    )
  }

  admin = false;
  changePss = false;
  checkLogin() {
    let token = localStorage.getItem('token');
    if (token != null) {
      this.changePss = true;
    }
  }

  email: string = "";
  




  bookingId:undefined;
  searchBooking()
  {
    if(this.bookingId != 0)
    {
      //sessionStorage.setItem('bookId',JSON.stringify(this.bookingId));
      this.router.navigate(['/searchbooking',this.bookingId])
    }
  }


  updateProfile()
  {
     this.router.navigate(['/updateprofile',this.id]);
  }

}
