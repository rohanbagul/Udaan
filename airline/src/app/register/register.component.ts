import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { User } from 'src/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  user: User = new User();

  isUser = false;
  isEmail = false;
  isPassword = false;
  isEmailValid = false;
  isregister = false;
  isExist = false;

  constructor(private userService: UserService, public router: Router) {

    this.myFormValidation();

  }



  addUser(): void {

    if (this.addForm.valid) {
      console.log(this.user);
      this.userService.addUser(this.user).subscribe(
        data => {
          let myJson = JSON.parse(JSON.stringify(data));
          this.isregister = true;
          this.router.navigate(['/login']);
        },
        error => {
          if (error.status == 409) {
            this.isExist = true;
          }
        }
      )
    }
    else{
      alert('Please fill all the field');
      alert('Please fill all the field.')
    }
    
  }


  myFormValidation() {
    this.addForm.controls['firstname'].setValidators([Validators.required, Validators.nullValidator]);
    this.addForm.controls['lastname'].setValidators([Validators.required, Validators.nullValidator]);
    this.addForm.controls['email'].setValidators([Validators.required, Validators.email]);
    this.addForm.controls['password'].setValidators([Validators.required]);
  }

  addForm = new FormGroup(
    {
      firstname: new FormControl(),
      lastname: new FormControl(),
      email: new FormControl(),
      password: new FormControl()

    }
  )

}
