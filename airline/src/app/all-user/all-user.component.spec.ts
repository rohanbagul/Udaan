import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { AllUserComponent } from './all-user.component';
import { UserService } from '../services/user.service';
import { User } from 'src/user.model';
import { AdminNavbarComponent } from '../admin-navbar/admin-navbar.component';

describe('AllUserComponent', () => {
  let component: AllUserComponent;
  let fixture: ComponentFixture<AllUserComponent>;
  let userService: UserService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AllUserComponent,AdminNavbarComponent],
      imports: [HttpClientTestingModule],
      providers: [UserService]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllUserComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should call getAllUsers on initialization', () => {
    const spy = spyOn(userService, 'getUsers').and.returnValue(of([]));
    component.ngOnInit();
    expect(spy).toHaveBeenCalled();
  });

  it('should retrieve all users from the service', () => {
    const users: User[] = [
      { id: 1,firstname:'rohan',lastname:'bagul',email:'rohan@gmail.com',password:'12345',role:'user',logged:true },
      { id: 2,firstname:'abc',lastname:'xyz',email:'abc@gmail.com',password:'12345',role:'user',logged:true },
      { id: 3,firstname:'cdg',lastname:'kdl',email:'cdg@gmail.com',password:'12345',role:'user',logged:true },
    ];
    spyOn(userService, 'getUsers').and.returnValue(of(users));
    component.getAllUsers();
    expect(component.users).toEqual(users);
  });

  it('should remove a user from the list', () => {
    const users: User[] = [
      { id: 1,firstname:'rohan',lastname:'bagul',email:'rohan@gmail.com',password:'12345',role:'user',logged:true },
    ];
    spyOn(userService, 'getUsers').and.returnValue(of(users));
    spyOn(userService, 'removeUser').and.returnValue(of({}));
    component.getAllUsers();
    component.removeUser(3);
    expect(component.users.length).toEqual(1);
    expect(component.users.map(u => u.id)).not.toContain(3);
  });

  
});
