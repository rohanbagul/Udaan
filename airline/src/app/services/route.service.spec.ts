import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Route1 } from 'src/route.model';
import { RouteService } from './route.service';

describe('RouteService', () => {
  let service: RouteService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [RouteService]
    });

    service = TestBed.inject(RouteService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should add a route', () => {
    const route:Route1 = { id: 1,routeCode:'acd234', source: 'A', destination: 'B' };
    service.addRoute(route).subscribe(result => {
      expect(result).toEqual(route);
    });

    const req = httpMock.expectOne('http://localhost:9191/api/v2/addRoute');
    expect(req.request.method).toBe('POST');
    req.flush(route);
  });

  it('should get routes by source', () => {
    const routes:Route1[] = [{ id: 1,routeCode:'acd234', source: 'A', destination: 'B' }];
    const source = 'A';
    service.getRouteBySource(source).subscribe(result => {
      expect(result).toEqual(routes);
    });

    const req = httpMock.expectOne(`http://localhost:9191/api/v2/getRouteBySource/${source}`);
    expect(req.request.method).toBe('GET');
    req.flush(routes);
  });

  it('should get all routes', () => {
    const routes:Route1[] = [{ id: 1,routeCode:'acd234', source: 'A', destination: 'B' }];
    service.getAllRoutes().subscribe(result => {
      expect(result).toEqual(routes);
    });

    const req = httpMock.expectOne('http://localhost:9191/api/v2/getRoute');
    expect(req.request.method).toBe('GET');
    req.flush(routes);
  });

  it('should remove a route', () => {
    const id = 1;
    service.removeRoute(id).subscribe();

    const req = httpMock.expectOne(`http://localhost:9191/api/v2/deleteroute/${id}`);
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });
});
