import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';
import { User } from 'src/user.model';

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should add user', () => {
    const user: User = {    id:1,
      firstname:'rohan',
      lastname:'bagul',
      email:'rohan@gmail.com',
      password:'123456',
      role:'USER',
      logged:true
  };
    service.addUser(user).subscribe(
      res => {
        expect(res).toBeTruthy();
      },
      error => {
        fail(error);
      }
    );

    const req = httpMock.expectOne('http://localhost:9191/api/v1/auth/register');
    expect(req.request.method).toEqual('POST');
    req.flush('User added successfully');
  });

  it('should login user', () => {
    const user: User = {id:1,
      firstname:'rohan',
      lastname:'bagul',
      email:'rohan@gmail.com',
      password:'123456',
      role:'USER',
      logged:true};
    service.login(user).subscribe(
      res => {
        expect(res).toBeTruthy();
      },
      error => {
        fail(error);
      }
    );

    const req = httpMock.expectOne('http://localhost:9191/api/v1/auth/login');
    expect(req.request.method).toEqual('PUT');
    req.flush('User logged in successfully');
  });

  it('should get user by email', () => {
    const email = 'test@test.com';
    service.getUser_1(email).subscribe(
      res => {
        expect(res).toBeTruthy();
      },
      error => {
        fail(error);
      }
    );

    const req = httpMock.expectOne(`http://localhost:9191/api/v1/user/getUser/${email}`);
    expect(req.request.method).toEqual('GET');
    req.flush('User retrieved successfully');
  });

  it('should change user password', () => {
    const user: User = {id:1,
      firstname:'rohan',
      lastname:'bagul',
      email:'rohan@gmail.com',
      password:'123456',
      role:'USER',
      logged:true};
    service.changePassword(user).subscribe(
      res => {
        expect(res).toBeTruthy();
      },
      error => {
        fail(error);
      }
    );

    const req = httpMock.expectOne('http://localhost:9191/api/v1/auth/changePassword');
    expect(req.request.method).toEqual('PUT');
    req.flush('Password changed successfully');
  });

  it('should get users', () => {
    service.getUsers().subscribe(
      res => {
        expect(res).toBeTruthy();
      },
      error => {
        fail(error);
      }
    );

    const req = httpMock.expectOne('http://localhost:3000/user');
    expect(req.request.method).toEqual('GET');
    req.flush('Users retrieved successfully');
  });

  it('should remove user by id', () => {
    const id = 1;
    service.removeUser(id).subscribe(
      res => {
        expect(res).toBeTruthy();
      },
      error => {
        fail(error);
      }
    );

    const req = httpMock.expectOne(`http://localhost:9191/api/v1/auth/deletebyid/${id}`);
    expect(req.request.method).toEqual('DELETE');
    req.flush('User deleted successfully!');
  });
});
