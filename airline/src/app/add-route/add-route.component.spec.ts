import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { of, throwError } from 'rxjs';
import { Route1 } from 'src/route.model';
import { AdminNavbarComponent } from '../admin-navbar/admin-navbar.component';
import { RouteService } from '../services/route.service';

import { AddRouteComponent } from './add-route.component';

describe('AddRouteComponent', () => {
  let component: AddRouteComponent;
  let fixture: ComponentFixture<AddRouteComponent>;
  let mockRouteService: jasmine.SpyObj<RouteService>;
  let mockRouter: jasmine.SpyObj<Router>;

  beforeEach(async () => {
    mockRouteService = jasmine.createSpyObj<RouteService>('RouteService', ['addRoute']);
    mockRouter = jasmine.createSpyObj<Router>('Router', ['navigate']);

    await TestBed.configureTestingModule({
      declarations: [ AddRouteComponent,AdminNavbarComponent ],
      imports:[FormsModule,HttpClientTestingModule],
      providers: [
        { provide: RouteService, useValue: mockRouteService },
        { provide: Router, useValue: mockRouter }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should add a route and navigate to home when addRoute() is called', () => {
    const route: Route1 = {
      id: 1,
      routeCode:'ab12345',
      source: 'New York',
      destination: 'Los Angeles'
    };
    mockRouteService.addRoute.and.returnValue(of(route));

    component.route = route;
    component.addRoute();

    expect(mockRouteService.addRoute).toHaveBeenCalledWith(route);
    expect(mockRouter.navigate).toHaveBeenCalledWith(['/allroutes']);
  });

  it('should show an error message when adding a route that already exists', () => {
    const error = new Error('Route already exists!');
    mockRouteService.addRoute.and.returnValue(throwError(error));
    spyOn(window, 'alert');

    component.route = {
      id: 1,
      routeCode:'ab12345',
      source: 'New York',
      destination: 'Los Angeles'
    };
    component.addRoute();

    expect(mockRouteService.addRoute).toHaveBeenCalledWith(component.route);
    expect(window.alert).toHaveBeenCalledWith('Route already exist!');
  });
});
