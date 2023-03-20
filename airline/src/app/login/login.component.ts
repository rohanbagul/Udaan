import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/user.model';
import { AllRoutesComponent } from '../all-routes/all-routes.component';
import { HomeComponent } from '../home/home.component';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();
  isLogin = false;
  isLog = false;

  isEmail = false;
  isPassword = false;
  isregister = false;
  isRole = false;
  rol = "";
  addRole=['Admin','User'];
  selectedRole = "Select Role"
  addForm: any;

  

  constructor(private userService: UserService, private router: Router,private fb:FormBuilder) {
    
    // this.myFormValidation();
  }
  ngOnInit(): void {
    this.addForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      role:[]
    });
  }
  

getToken()
{
  console.log(localStorage.getItem('token'));
  return localStorage.getItem('token');
}

  loginUser() {
    if (this.addForm.valid) {
      localStorage.setItem('userEmail',this.user.email)
      console.log(this.user.email);
      this.userService.login(this.user).subscribe(
        data => {
          console.log(data)
          let myJson = JSON.parse(JSON.stringify(data));
          if(myJson.token != null)
          {
            if(myJson.token == "You are alreday login")
            {
              alert("You have Logged in Successfully!")
            }
            localStorage.setItem('token',myJson.token);
            this.router.navigate(['/home']);
          }
        },
        error => {
          alert('Invalid Email Or Password!!')
          console.log(error);
        }
      )
    }
    
  }



  get email() { return this.addForm.get('email'); }
  get password() { return this.addForm.get('password'); }


}
