import { TestBed } from '@angular/core/testing';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { UserService } from '../services/user.service';
import { AuthGuard } from './auth.guard';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let userServiceSpy: jasmine.SpyObj<UserService>;
  let routerSpy: jasmine.SpyObj<Router>;
  let routeSnapshot: ActivatedRouteSnapshot;
  let stateSnapshot: RouterStateSnapshot;

  beforeEach(() => {
    const userService = jasmine.createSpyObj('UserService', ['isLogedIn']);
    const router = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      providers: [
        AuthGuard,
        { provide: UserService, useValue: userService },
        { provide: Router, useValue: router },
      ]
    });
    guard = TestBed.inject(AuthGuard);
    userServiceSpy = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
    routerSpy = TestBed.inject(Router) as jasmine.SpyObj<Router>;

    routeSnapshot = jasmine.createSpyObj<ActivatedRouteSnapshot>('ActivatedRouteSnapshot', ['toString']);
    stateSnapshot = jasmine.createSpyObj<RouterStateSnapshot>('RouterStateSnapshot', ['toString']);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should return true if the user is logged in', () => {
    userServiceSpy.isLogedIn.and.returnValue(true);

    const result = guard.canActivate(routeSnapshot, stateSnapshot);

    expect(result).toBeTrue();
    expect(userServiceSpy.isLogedIn).toHaveBeenCalled();
    expect(routerSpy.navigate).not.toHaveBeenCalled();
  });

  it('should return false and redirect to login page if the user is not logged in', () => {
    userServiceSpy.isLogedIn.and.returnValue(false);

    const result = guard.canActivate(routeSnapshot, stateSnapshot);

    expect(result).toBeFalse();
    expect(userServiceSpy.isLogedIn).toHaveBeenCalled();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/login']);
  });
});
