import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { UpdateProfileComponent } from './update-profile.component';
import { User } from 'src/user.model';
import { UserService } from '../services/user.service';
import { NavbarComponent } from '../navbar/navbar.component';

describe('UpdateProfileComponent', () => {
  let component: UpdateProfileComponent;
  let fixture: ComponentFixture<UpdateProfileComponent>;
  let userService: UserService;
  let router: Router;
  let activatedRoute: ActivatedRoute;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateProfileComponent,NavbarComponent ],
      imports: [ ReactiveFormsModule, HttpClientTestingModule,FormsModule ],
      providers: [ UserService, {
        provide: ActivatedRoute,
        useValue: {
          params: of({ id: '1' })
        }
      }]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateProfileComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService);
    router = TestBed.inject(Router);
    activatedRoute = TestBed.inject(ActivatedRoute);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call gatPathVar method and set id', () => {
    spyOn(activatedRoute.params, 'subscribe').and.callThrough();
    component.gatPathVar();
    expect(activatedRoute.params.subscribe).toHaveBeenCalled();
  });

  it('should set form validators on ngOnInit', () => {
    spyOn(component, 'myFormValidation').and.callThrough();
    component.ngOnInit();
    expect(component.myFormValidation).toHaveBeenCalled();
  });

  it('should create form with correct fields', () => {
    expect(component.addForm.contains('firstname')).toBeTrue();
    expect(component.addForm.contains('lastname')).toBeTrue();
    expect(component.addForm.contains('email')).toBeTrue();
  });

  it('should not allow submission of invalid form', () => {
    component.addForm.controls['firstname'].setValue('');
    component.addForm.controls['lastname'].setValue('');
    component.addForm.controls['email'].setValue('');
    expect(component.addForm.valid).toBeFalse();
    spyOn(window, 'alert');
    component.addUser();
    expect(window.alert).toHaveBeenCalledWith('Please fill all the field');
  });

  it('should call updateProfile method in UserService on valid form submission and navigate to login page on success', () => {
    const user: User = new User();
    user.firstname = 'John';
    user.lastname = 'Doe';
    user.email = 'john.doe@test.com';
    component.user = user;
    component.addForm.controls['firstname'].setValue('John');
    component.addForm.controls['lastname'].setValue('Doe');
    component.addForm.controls['email'].setValue('john.doe@test.com');
    spyOn(userService, 'updateProfile').and.returnValue(of({}));
    spyOn(router, 'navigate');
    component.addUser();
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });
});
