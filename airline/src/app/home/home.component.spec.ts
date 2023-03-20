import { HttpClientTestingModule } from '@angular/common/http/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { of } from 'rxjs';
import { Route1 } from 'src/route.model';
import { FooterComponent } from '../footer/footer.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouteService } from '../services/route.service';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  let routeService:RouteService;
  const mockRoutes: Route1[] = [
    { id: 1,routeCode:'abc123', source: 'Bangalore', destination: 'Pune' },
    { id: 2,routeCode:'xyz321', source: 'Bangalore', destination: 'Mumbai' },
    { id: 3,routeCode:'abc456', source: 'Pune', destination: 'Mumbai'}];
  
  
  

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[HttpClientTestingModule, FormsModule],
      declarations: [ HomeComponent, NavbarComponent, FooterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    routeService = TestBed.inject(RouteService);

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call search method when search button is clicked', () => {
    spyOn(component, 'search');
    const button = fixture.debugElement.nativeElement.querySelector('button');
    button.click();
    fixture.whenStable().then(() => {
      expect(component.search).toHaveBeenCalled();
    });
  });

  it('should store leave and arrive values in localStorage when submit is called', () => {
    spyOn(localStorage, 'setItem');
    component.leave = 'Mumbai';
    component.arrive = 'Pune';
    component.submit();
    expect(localStorage.setItem).toHaveBeenCalledWith('arrive', 'Mumbai');
    expect(localStorage.setItem).toHaveBeenCalledWith('leave', 'Pune');
  });

  it('should show an alert when leave value is not selected', () => {
    spyOn(window, 'alert');
    component.leave = 'Select Leaving Location';
    component.arrive = 'Mumbai';
    component.submit();
    expect(window.alert).toHaveBeenCalledWith('Please Sealct Leaving Location');
  });

  it('should show an alert when arrive value is not selected', () => {
    spyOn(window, 'alert');
    component.leave = 'Pune';
    component.arrive = 'Select Arrival Location';
    component.submit();
    expect(window.alert).toHaveBeenCalledWith('Please Select Arrival Location');
  });

  it('should show an alert when leave and arrive values are the same', () => {
    spyOn(window, 'alert');
    component.leave = 'Pune';
    component.arrive = 'Pune';
    component.submit();
    expect(window.alert).toHaveBeenCalledWith('Please Select different arrival and leaving locations.');
  });

  it('should fetch routes successfully when getRouteBySource is called', () => {
    spyOn(routeService, 'getRouteBySource').and.returnValue(of(mockRoutes));
    component.getRouteBySource('Bangalore');
    expect(component.routes).toEqual(mockRoutes);
  });

  it('should fetch all sources successfully when getAllSources is called', () => {
    spyOn(routeService, 'getAllRoutes').and.returnValue(of(mockRoutes));
    component.getAllSources();
    expect(component.allSources).toEqual(['Bangalore', 'Pune']);
  });

  it('should fetch all destinations successfully when getAllDestinations is called', () => {
    spyOn(routeService, 'getAllRoutes').and.returnValue(of(mockRoutes));
    component.getAllDestinations();
    expect(component.alldestinations).toEqual(['Pune', 'Mumbai']);
  });
  
  
  

  
});
