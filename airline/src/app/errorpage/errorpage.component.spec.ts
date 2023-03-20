import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorpageComponent } from './errorpage.component';

describe('ErrorpageComponent', () => {
  let component: ErrorpageComponent;
  let fixture: ComponentFixture<ErrorpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErrorpageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ErrorpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the "404 Page Not Found" text', () => {
    const text = fixture.nativeElement.querySelector('h1').textContent;
    expect(text).toContain('404 Page Not Found');
  });
});
