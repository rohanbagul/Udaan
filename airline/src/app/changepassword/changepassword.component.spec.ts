import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { UserService } from '../services/user.service';
import { ChangepasswordComponent } from './changepassword.component';
import { User } from 'src/user.model';
import { of, throwError } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NavbarComponent } from '../navbar/navbar.component';

describe('ChangepasswordComponent', () => {
  let component: ChangepasswordComponent;
  let fixture: ComponentFixture<ChangepasswordComponent>;
  let userService: UserService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, RouterTestingModule,HttpClientTestingModule,FormsModule],
      declarations: [ChangepasswordComponent,NavbarComponent],
      providers: [UserService]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangepasswordComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should set validation for email and password fields', () => {
    expect(component.addForm.controls['email'].valid).toBeFalsy();
    expect(component.addForm.controls['password'].valid).toBeFalsy();
    component.myFormValidation();
  });

  it('should not call changePassword method when form is invalid', () => {
    spyOn(userService, 'changePassword');
    component.addUser();
    expect(userService.changePassword).not.toHaveBeenCalled();
  });

  it('should call changePassword method when form is valid', () => {
    const user = new User();
    component.user = user;
    component.addForm.setValue({email: 'test@test.com',name:'test', password: 'password'});
    spyOn(userService, 'changePassword').and.returnValue(of({}));
    component.addUser();
    expect(userService.changePassword).toHaveBeenCalled();
  });

  it('should set isregister to true and navigate to login page when password is updated successfully', () => {
    const user = new User();
    component.user = user;
    component.addForm.setValue({email: 'test@test.com',name:'test', password: 'password'});
    spyOn(userService, 'changePassword').and.returnValue(of({}));
    spyOn(component.router, 'navigate');
    component.addUser();
    expect(component.isregister).toBeTruthy();
    expect(component.router.navigate).toHaveBeenCalledWith(['/login']);
    expect(localStorage.length).toBe(0);
  });

  it('should show alert message when there is an error updating password', () => {
    const user = new User();
    component.user = user;
    component.addForm.setValue({email: 'test@test.com',name:'test', password: 'password'});
    spyOn(userService, 'changePassword').and.returnValue(
      throwError({ status: 404, message: 'Page Not Found' })
    );
    spyOn(window, 'alert');
    component.addUser();
    expect(window.alert).toHaveBeenCalledWith('Page Not Found');
  });

  it('should call the UserService.changePassword method and navigate to login page if form is valid', () => {
    spyOn(userService, 'changePassword').and.returnValue(of({}));
    spyOn(component.router, 'navigate');

    component.addForm.setValue({
      name: 'test',
      email: 'test@example.com',
      password: 'password123'
    });

    component.addUser();

    expect(userService.changePassword).toHaveBeenCalled();
    expect(component.isregister).toBeTrue();
    expect(component.router.navigate).toHaveBeenCalledWith(['/login']);
  });

  it('should set the isEmailValid flag to true if email is not valid', () => {
    component.addForm.setValue({
      name: 'test',
      email: 'invalid email',
      password: 'password123'
    });

    component.addUser();

    expect(component.isEmailValid).toBeTrue();
  });

  it('should set the isEmail flag to true if email is not provided', () => {
    component.addForm.setValue({
      name: 'test',
      email: '',
      password: 'password123'
    });

    component.addUser();

    expect(component.isEmail).toBeFalse();
  });

  it('should display an alert with "Page Not Found" if the UserService.changePassword method throws an error', () => {
    spyOn(userService, 'changePassword').and.returnValue(
      throwError({status: 404})
    );
    spyOn(window, 'alert');

    component.addForm.setValue({
      name: 'test',
      email: 'test@example.com',
      password: 'password123'
    });

    component.addUser();

    expect(window.alert).toHaveBeenCalledWith('Page Not Found');
  });



});
