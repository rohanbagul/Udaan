import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
  user: User = new User();

  isUser = false;
  isEmail = false;
  isPassword = false;
  isEmailValid = false;
  isExist = false;

  constructor(private userService: UserService, private router: Router, private actv:ActivatedRoute) {

    this.myFormValidation();

  }

  ngOnInit(): void {
    this.myFormValidation();
   this.gatPathVar();
  }

 id:number = 0;
  gatPathVar()
  {
      this.actv.params.subscribe(
        data => {
          this.id = data['id'];
        }
      )
  }


  addUser(): void {

    if (this.addForm.valid) {
      console.log(this.user);
      this.userService.updateProfile(this.id,this.user).subscribe(
        data => {
          let myJson = JSON.parse(JSON.stringify(data));
          alert("Profile updated sucessfully !!");
          this.router.navigate(['/login']);
        },
        error => {
          console.log(error);
        }
      )
    }
    else{
      alert('Please fill all the field');
    }
    
  }


  myFormValidation() {
    this.addForm.controls['firstname'].setValidators([Validators.required, Validators.nullValidator]);
    this.addForm.controls['lastname'].setValidators([Validators.required, Validators.nullValidator]);
    this.addForm.controls['email'].setValidators([Validators.required, Validators.email]);
    
  }

  addForm = new FormGroup(
    {
      firstname: new FormControl(),
      lastname: new FormControl(),
      email: new FormControl(),
      
    }
  )

}
