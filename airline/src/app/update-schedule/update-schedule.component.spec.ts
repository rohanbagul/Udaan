import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { UpdateScheduleComponent } from './update-schedule.component';
import { ScheduleService } from '../services/schedule.service';
import { RouteService } from '../services/route.service';
import { Schedule } from 'src/schedule.model';
import { Route1 } from 'src/route.model';
import { of } from 'rxjs';
import { AdminNavbarComponent } from '../admin-navbar/admin-navbar.component';

describe('UpdateScheduleComponent', () => {
  let component: UpdateScheduleComponent;
  let fixture: ComponentFixture<UpdateScheduleComponent>;
  let scheduleService: ScheduleService;
  let routeService: RouteService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateScheduleComponent,AdminNavbarComponent ],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        FormsModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateScheduleComponent);
    component = fixture.componentInstance;
    scheduleService = TestBed.inject(ScheduleService);
    routeService = TestBed.inject(RouteService);
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should call getAllSources() and getAllDestinations() on ngOnInit()', () => {
    const getAllSourcesSpy = spyOn(component, 'getAllSources');
    const getAllDestinationsSpy = spyOn(component, 'getAllDestinations');
    component.ngOnInit();
    expect(getAllSourcesSpy).toHaveBeenCalled();
    expect(getAllDestinationsSpy).toHaveBeenCalled();
  });

  it('should update the schedule when submit() is called', () => {
    const updateScheduleSpy = spyOn(scheduleService, 'updateSchedule').and.returnValue(of({}));
    const routerNavigateSpy = spyOn(component.router, 'navigate');
    component.schedule = new Schedule();
    component.schduleFlight = new Schedule();
    component.submit();
    expect(updateScheduleSpy).toHaveBeenCalled();
    expect(routerNavigateSpy).toHaveBeenCalledWith(['/allschedule']);
  });

  it('should set allSources and alldestinations when getAllSources() and getAllDestinations() are called', () => {
    const routes: Route1[] = [
      { id:1,
        routeCode:'abc1234',
        source:'Pune',
        destination:'Banglore',
      },
      { id:2,
        routeCode:'xyz355',
        source:'Delhi',
        destination:'Surat', },
        { id:2,
          routeCode:'xyz355',
          source:'Chennai',
          destination:'Nagpur', }
     
    ];
    spyOn(routeService, 'getAllRoutes').and.returnValue(of(routes));
    component.getAllSources();
    expect(component.allSources).toEqual(['Pune', 'Delhi','Chennai']);
    component.getAllDestinations();
    expect(component.alldestinations).toEqual(['Banglore', 'Surat', 'Nagpur']);
  });

});
