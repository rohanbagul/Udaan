import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { AllRoutesComponent } from './all-routes.component';
import { Route1 } from 'src/route.model';
import { RouteService } from '../services/route.service';
import { of, throwError } from 'rxjs';
import { AdminNavbarComponent } from '../admin-navbar/admin-navbar.component';

describe('AllRoutesComponent', () => {
  let component: AllRoutesComponent;
  let fixture: ComponentFixture<AllRoutesComponent>;
  let routeService: jasmine.SpyObj<RouteService>;

  beforeEach(async () => {
    routeService = jasmine.createSpyObj('RouteService', ['getAllRoutes', 'removeRoute']);

    await TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [ AllRoutesComponent,AdminNavbarComponent ],
      providers: [ { provide: RouteService, useValue: routeService } ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllRoutesComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should get all routes on init', () => {
    const routes: Route1[] = [
      { id: 1,routeCode:'abc1234', source: 'New York', destination: 'London' },
      { id: 2,routeCode:'abc1235', source: 'London', destination: 'Paris' }
    ];
    routeService.getAllRoutes.and.returnValue(of(routes));

    fixture.detectChanges();

    expect(component.allroutes).toEqual(routes);
  });

  it('should handle error when getting all routes', () => {
    const error = new Error('Failed to get routes');
    routeService.getAllRoutes.and.returnValue(throwError(error));

    spyOn(console, 'log');

    fixture.detectChanges();

    expect(console.log).toHaveBeenCalledWith(error);
  });

  it('should remove route', () => {
    const routeId = 1;
    routeService.removeRoute.and.returnValue(of());

    spyOn(window, 'alert');

    component.removeRoute(routeId);

    expect(routeService.removeRoute).toHaveBeenCalledWith(routeId);
    expect(component.allroutes.length).toBe(0);
  });

  it('should handle error when removing route', () => {
    const routeId = 1;
    const error = new Error('Failed to remove route');
    routeService.removeRoute.and.returnValue(throwError(error));

    spyOn(console, 'log');

    component.removeRoute(routeId);

    expect(routeService.removeRoute).toHaveBeenCalledWith(routeId);
    expect(console.log).toHaveBeenCalledWith(error);
  });

  
});
