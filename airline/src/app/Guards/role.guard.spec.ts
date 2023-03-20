import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { RoleGuard } from './role.guard';
import { UserService } from '../services/user.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { User } from 'src/user.model';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

describe('RoleGuard', () => {
  let guard: RoleGuard;
  let mockUserService: any;
  let route: ActivatedRouteSnapshot;
  let state: RouterStateSnapshot;


  beforeEach(() => {
    mockUserService = jasmine.createSpyObj('UserService', ['getUser_1']);
    route = {} as ActivatedRouteSnapshot;
    state = {} as RouterStateSnapshot;

    TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule],
      providers: [
        RoleGuard,
        { provide: UserService, useValue: mockUserService }
      ]
    });

    guard = TestBed.inject(RoleGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should return true if user is an admin', () => {
    const mockUser: User = {
      email: 'rohan@test.com', password: 'password', role: 'ADMIN',
      id: 1,
      firstname: 'rohan',
      lastname: 'bagul',
      logged: true
    };
    mockUserService.getUser_1.and.returnValue(of(mockUser));
    
    const canActivate = guard.canActivate(route,state);
    
    expect(canActivate).toBeFalse();
  });

  it('should return false if user is not an admin', () => {
    const mockUser: User = {
      email: 'smitesh@test.com', password: 'password', role: 'USER',
      id: 2,
      firstname: 'smitesh',
      lastname: 'Joshi',
      logged: true
    };    mockUserService.getUser_1.and.returnValue(of(mockUser));
    
    const canActivate = guard.canActivate(route,state);
    
    expect(canActivate).toBeFalse();
  });
});
