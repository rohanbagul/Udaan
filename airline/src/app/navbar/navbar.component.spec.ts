import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NavbarComponent } from './navbar.component';
import { UserService } from '../services/user.service';
import { User } from 'src/user.model';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;
  let userService: UserService;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ NavbarComponent ],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        FormsModule
      ],
      providers: [
        UserService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    userService = TestBed.inject(UserService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });



  it('should check display login status', () => {
    spyOn(localStorage, 'getItem').and.returnValue('fake-token');
    component.checkDisplayLoginStatus();
    expect(component.displayLoginStatus).toEqual('LogOut');
  });

  it('should log out', () => {
    spyOn(localStorage, 'clear');
    spyOn(component.router, 'navigate');
    const logOutSpy = spyOn(userService, 'logOut').and.returnValue(
      of({})
    );
    component.id = 1;
    component.displayLoginStatus = 'LogOut';
    component.logOut();
    expect(logOutSpy).toHaveBeenCalled();
    expect(localStorage.clear).toHaveBeenCalled();
    expect(component.displayLoginStatus).toEqual('LogIn');
    expect(component.router.navigate).toHaveBeenCalledWith(['/login']);
  });

  it('should remove account', () => {
    spyOn(localStorage, 'clear');
    spyOn(component.router, 'navigate');
    const removeUserSpy = spyOn(userService, 'removeUser').and.returnValue(
      of({})
    );
    component.id = 1;
    component.removeAccount();
    expect(removeUserSpy).toHaveBeenCalledWith(1);
    expect(localStorage.clear).toHaveBeenCalled();
    expect(component.router.navigate).toHaveBeenCalledWith(['/login']);
  });

  it('should check login status', () => {
    spyOn(localStorage, 'getItem').and.returnValue('fake-token');
    component.checkLogin();
    expect(component.changePss).toBeTrue();
  });

  it('should search for a booking', () => {
    component.bookingId = undefined;
    spyOn(component.router, 'navigate');
    component.searchBooking();
    expect(component.router.navigate).toHaveBeenCalledWith(['/searchbooking', undefined]);
  });

  it('should update profile', () => {
    component.id = 1;
    spyOn(component.router, 'navigate');
    component.updateProfile();
    expect(component.router.navigate).toHaveBeenCalledWith(['/updateprofile', 1]);
  });

  it('should navigate to "/login" when displayLoginStatus is "LogIn"', () => {
    // Arrange
    const routerSpy = jasmine.createSpyObj('Router', ['navigate']);
    const component = new NavbarComponent(routerSpy, userService);
    component.displayLoginStatus = 'LogIn';
  
    // Act
    component.logOut();
  
    // Assert
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/login']);
  });
  
  
  
  
});
