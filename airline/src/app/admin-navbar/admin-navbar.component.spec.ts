import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminNavbarComponent } from './admin-navbar.component';

describe('AdminNavbarComponent', () => {
  let component: AdminNavbarComponent;
  let fixture: ComponentFixture<AdminNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminNavbarComponent ],imports:[HttpClientTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should contain links to the home, allFlight, addFlight, addroute, allroutes, and allfeedback pages', () => {
    const links = fixture.nativeElement.querySelectorAll('a.nav-link');
    expect(links.length).toBe(7);
    expect(links[0].textContent).toContain('Home');
    expect(links[1].textContent).toContain('All Flight');
    expect(links[2].textContent).toContain('Add Flight');
    expect(links[3].textContent).toContain('Add Route');
    expect(links[4].textContent).toContain('All Route');
    expect(links[5].textContent).toContain('All Feedback');
  });

  it('should contain a link to the allschedule page within a dropdown menu', () => {
    const dropdownLinks = fixture.nativeElement.querySelectorAll('.dropdown-menu a');
    expect(dropdownLinks.length).toBe(1);
    expect(dropdownLinks[0].textContent).toContain('All Schdule Flight');
  });

  it('should contain a "logOut" button that links to the login page', () => {
    const logoutButton = fixture.nativeElement.querySelector('button');
    expect(logoutButton).toBeTruthy();
    expect(logoutButton.getAttribute('routerLink')).toBe(null);
  });
});
