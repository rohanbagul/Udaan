import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  constructor(public router: Router, private userService: UserService) {
    setInterval(() => 
    {
      alert("session expired !!!");
      this.logOut()
    }, 1440000)
  }

  ngOnInit(): void {
    this.getUser();
    this.checkDisplayLoginStatus();
    this.checkLogin();

    this.checkRole();
  }

  user: User = new User();
  u: User = new User();
  isLogin = false;
  displayLoginStatus = 'Login';
  id = 0;
  isrole: Boolean = false;

  getUser() {
    let token = localStorage.getItem('token');
    if (token != null) {
      let userEmail = localStorage.getItem('userEmail');
      if (userEmail != null) {
        this.userService.getUser_1(userEmail).subscribe(
          (data) => {
            let myJson = JSON.parse(JSON.stringify(data));
            this.id = myJson.id;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    }
  }

  checkDisplayLoginStatus() {
    let token = localStorage.getItem('token');
    if (token != null) {
      this.displayLoginStatus = 'Logout';
    }
  }

  logOut() {
    localStorage.clear();
    if (this.displayLoginStatus == 'LogIn') {
      this.router.navigate(['/login']);
    } else {
      this.userService.logOut(this.id).subscribe(
        (data) => {
          this.displayLoginStatus = 'LogIn';
          this.router.navigate(['/login']);
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }

  removeAccount() {
    this.userService.removeUser(this.id).subscribe(
      (data) => {
        alert('Your accont deleted sucessfully!');
        localStorage.clear();
        this.router.navigate(['/login']);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  admin = false;
  changePss = false;
  checkLogin() {
    let token = localStorage.getItem('token');
    if (token != null) {
      this.changePss = true;
    }
  }

  email: string = '';

  bookingId: undefined;
  searchBooking() {
    if (this.bookingId != 0) {
      //sessionStorage.setItem('bookId',JSON.stringify(this.bookingId));
      this.router.navigate(['/searchbooking', this.bookingId]);
    }
  }

  updateProfile() {
    this.router.navigate(['/updateprofile', this.id]);
  }
  checkRole() {
    let token = localStorage.getItem('token');
    if (token != null) {
      let str = localStorage.getItem('userEmail');
      if (str != null) {
        this.userService.getUser_1(str).subscribe((data) => {
          this.u = data;

          if (this.u.role === 'ADMIN') {
            this.isrole = true;
          }
        });
      }
    }
    if (this.u.role === 'ADMIN') {
      return true;
    }
    return false;
  }

  getMyBooking() {
    if (this.id != null) {
      this.router.navigate(['/mybooking', this.id]);
    }
  }
}
