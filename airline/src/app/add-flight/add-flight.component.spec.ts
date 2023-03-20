import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule, RequiredValidator, Validators } from '@angular/forms';
import { of } from 'rxjs';
import { FlightService } from '../services/flight.service';

import { AddFlightComponent } from './add-flight.component';
import { Flight } from 'src/flight.model';
import { AdminNavbarComponent } from '../admin-navbar/admin-navbar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AddFlightComponent', () => {
  let component: AddFlightComponent;
  let fixture: ComponentFixture<AddFlightComponent>;
  let flightService: jasmine.SpyObj<FlightService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('FlightService', ['addFlight']);

    await TestBed.configureTestingModule({
      declarations: [ AddFlightComponent,AdminNavbarComponent ],
      imports: [ ReactiveFormsModule,FormsModule,HttpClientTestingModule ],
      providers: [ { provide: FlightService, useValue: spy } ]
    })
    .compileComponents();

    flightService = TestBed.inject(FlightService) as jasmine.SpyObj<FlightService>;
  });
  beforeEach(() => {
    fixture = TestBed.createComponent(AddFlightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set form validators on myFormValidation', () => {
    // Arrange
    component.addForm.controls['flightName'].clearValidators();
    component.addForm.controls['flightNumber'].clearValidators();
    component.addForm.controls['EconomySeat'].clearValidators();
    component.addForm.controls['primiumSeat'].clearValidators();
    component.addForm.controls['bussinessSeat'].clearValidators();

    // Act
    component.myFormValidation();

    // Assert
    expect(component.addForm.controls['flightName'].hasValidator(Validators.required)).toBeTrue();
    expect(component.addForm.controls['flightNumber'].hasValidator(Validators.required)).toBeTrue();
    expect(component.addForm.controls['EconomySeat'].hasValidator(Validators.required)).toBeTrue();
    expect(component.addForm.controls['primiumSeat'].hasValidator(Validators.required)).toBeTrue();
    expect(component.addForm.controls['primiumSeat'].hasValidator(Validators.required)).toBeTrue();

  });

  
  
});
