import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { RouterLinkWithHref } from '@angular/router';
import { Location } from '@angular/common';
import { RegisterComponent } from './register.component';
import { RouterTestingModule } from '@angular/router/testing';
import { routes } from '../app-routing.module';
import { User } from 'src/user.model';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let location:Location;

  beforeEach(async () => {
    await TestBed.configureTestingModule({imports:[HttpClientTestingModule,ReactiveFormsModule,FormsModule,RouterTestingModule.withRoutes(routes)],
      declarations: [ RegisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Should create a register form with email and password controls', () => {
    expect(component.addForm.contains('email')).toBeTruthy();
		expect(component.addForm.contains('password')).toBeTruthy();

	});

  it('Should make email control required', () => {
		const control = component.addForm.get('email');

		control?.setValue('');

		expect(control?.valid).toBeFalsy();
	});

  it('Should make password control required', () => {
		const control = component.addForm.get('password');

		control?.setValue('');

		expect(control?.valid).toBeFalsy();
	});


  it('unit test case for register route', () => {
    fixture.detectChanges();
    let link =fixture.debugElement.queryAll(By.directive(RouterLinkWithHref));
    fixture.whenStable().then(()=>{
      expect(location.path()).toEqual('/login')
    })
    });

    it('should display "Please fill all the field" alert if the form is invalid', () => {
      spyOn(window, 'alert');
  
      component.addUser();
  
      expect(window.alert).toHaveBeenCalledWith('Please fill all the field');
    });
  

});

