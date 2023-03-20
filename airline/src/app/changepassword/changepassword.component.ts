import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent {
  user: User = new User();

  isUser = false;
  isEmail = false;
  isPassword = false;
  isEmailValid = false;
  isregister = false;

  constructor(private userService: UserService, public router: Router) {

    this.myFormValidation();

  }



  addUser(): void {
    if (this.addForm.valid) {
      this.userService.changePassword(this.user).subscribe(
        data => {
          let myJson = JSON.parse(JSON.stringify(data));
          this.isregister = true;
          alert('password updated Sucessfully');
          this.router.navigate(['/login']);
          localStorage.clear();
        },
        error => {
          alert("Page Not Found");
        }


      )
    }
    else {
      if (this.addForm.controls['email'].errors != null || this.addForm.controls['password'] != null) {


        if (this.addForm.controls['email'].errors != null) {
          if (this.addForm.controls['email'].invalid == true) {
            this.isEmailValid = true;
          }
          else {
            this.isEmail = true;
          }
        }
        else {
          this.isPassword = true;
        }
      }
    }
  }


  myFormValidation() {
    this.addForm.controls['email'].setValidators([Validators.required, Validators.email]);
    this.addForm.controls['password'].setValidators([Validators.required]);
  }

  addForm = new FormGroup(
    {
      name: new FormControl(),
      email: new FormControl(),
      password: new FormControl()

    }
  )

}
