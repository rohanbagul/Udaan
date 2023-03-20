import { Component, OnInit } from '@angular/core';
import { User } from 'src/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-all-user',
  templateUrl: './all-user.component.html',
  styleUrls: ['./all-user.component.css']
})
export class AllUserComponent implements OnInit{

  constructor(private userService:UserService){

  }
  ngOnInit(): void {
    this.getAllUsers();
  }

  users:User[] = [];

  getAllUsers()
  {
    this.userService.getUsers().subscribe(
      data => {
        this.users = data;
      },
      error => {
        console.log(error);
      }
    )
  }

  removeUser(id:number)
  {
    this.userService.removeUser(id).subscribe(
      data => {
        console.log("user deleted sucessfully");
        this.getAllUsers();
      },

      error => {
        console.log(error);
      }
    )
  }

}
