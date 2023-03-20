import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { Flight } from 'src/flight.model';
import { Route1 } from 'src/route.model';
import { Schedule } from 'src/schedule.model';
import { AdminNavbarComponent } from '../admin-navbar/admin-navbar.component';
import { RouteService } from '../services/route.service';
import { ScheduleService } from '../services/schedule.service';

import { AddScheduleComponent } from './add-schedule.component';

describe('AddScheduleComponent', () => {
  let component: AddScheduleComponent;
  let fixture: ComponentFixture<AddScheduleComponent>;
  let mockScheduleService: jasmine.SpyObj<ScheduleService>;
  let mockRouteService: jasmine.SpyObj<RouteService>;

  const mockFlight: Flight = {
    id:1,
    flightName: 'Test Flight',
    flightNumber: 'TF001',
    seats: [],
  };

  const mockRoutes: Route1[] = [
    { id:1,routeCode:'abcd1234',source: 'pune', destination: 'delhi' },
    { id:2,routeCode:'jdjd3434',source: 'chennai', destination: 'nagpur' },
    { id:3,routeCode:'kjflkd34',source: 'surat', destination: 'banglore' },
  ];

  beforeEach(async () => {
    mockScheduleService = jasmine.createSpyObj('ScheduleService', ['addFlightSchedule']);
    mockRouteService = jasmine.createSpyObj('RouteService', ['getAllRoutes']);
    mockRouteService.getAllRoutes.and.returnValue(of(mockRoutes));

    await TestBed.configureTestingModule({
      imports: [RouterTestingModule,FormsModule,HttpClientTestingModule],
      declarations: [AddScheduleComponent,AdminNavbarComponent],
      providers: [
        { provide: ScheduleService, useValue: mockScheduleService },
        { provide: RouteService, useValue: mockRouteService },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddScheduleComponent);
    component = fixture.componentInstance;
    localStorage.setItem('flight', JSON.stringify(mockFlight));
    fixture.detectChanges();
  });

  afterEach(() => {
    localStorage.removeItem('flight');
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize flight1 from local storage', () => {
    expect(component.flight1).toEqual(mockFlight);
  });

 

  it('should initialize allSources from the routes', () => {
    expect(component.allSources).toEqual(['pune', 'chennai','surat']);
  });

  it('should initialize alldestinations from the routes', () => {
    expect(component.alldestinations).toEqual(['delhi', 'nagpur','banglore']);
  });

  it('should display success message on submit', () => {
    spyOn(window, 'alert');
    mockScheduleService.addFlightSchedule.and.returnValue(of({}));
    component.submit();
    expect(window.alert).toHaveBeenCalledWith('Flight schedule added sucessfully !!!');
  });

 });