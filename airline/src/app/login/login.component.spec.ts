import { async, ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { LoginComponent } from './login.component';
import { UserService } from '../services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { of, throwError } from 'rxjs';
import { ToastrConfig, ToastrService } from 'ngx-toastr';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let userService: UserService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [ ReactiveFormsModule, RouterTestingModule, HttpClientModule ],
      providers: [ UserService,ToastrService,
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the form with default values', () => {
    expect(component.addForm.value).toEqual({ email: undefined, password: undefined, role: null });
  });

  it('should set the email as required', () => {
    const email = component.addForm.controls['email'];
    email.setValue('');
    expect(email.valid).toBeFalsy();
    expect(email.errors?.['required']).toBeTruthy();
  });

  it('should set the password as required', () => {
    const password = component.addForm.controls['password'];
    password.setValue('');
    expect(password.valid).toBeFalsy();
    expect(password.errors?.['required']).toBeTruthy();
  });

  it('should call the login method on form submission with valid input', () => {
    spyOn(userService, 'login').and.returnValue(of({ token: 'sample_token' }));
    component.addForm.setValue({ email: 'test@example.com', password: 'password', role: 'Admin' });
    component.loginUser();
    expect(userService.login).toHaveBeenCalled();
  });

  it('should show an alert when login fails', () => {
    spyOn(window, 'alert');
    spyOn(userService, 'login').and.returnValue(throwError(''));
    component.addForm.setValue({ email: 'test@example.com', password: 'password', role: 'Admin' });
    component.loginUser();
    expect(window.alert).toHaveBeenCalledWith('Invalid Email Or Password!!');
  });

});
