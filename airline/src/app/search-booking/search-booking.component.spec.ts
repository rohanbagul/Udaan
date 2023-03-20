import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';

import { SearchBookingComponent } from './search-booking.component';

xdescribe('SearchBookingComponent', () => {
  let component: SearchBookingComponent;
  let fixture: ComponentFixture<SearchBookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchBookingComponent,NavbarComponent ],imports:[HttpClientTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
