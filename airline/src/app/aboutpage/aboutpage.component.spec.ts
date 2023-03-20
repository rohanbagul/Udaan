import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { NavbarComponent } from '../navbar/navbar.component';

import { AboutpageComponent } from './aboutpage.component';

describe('AboutpageComponent', () => {
  let component: AboutpageComponent;
  let fixture: ComponentFixture<AboutpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[HttpClientTestingModule,FormsModule
      ],
      declarations: [ AboutpageComponent,NavbarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AboutpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  

  it('should display the About section heading', () => {
    const heading = fixture.debugElement.query(By.css('.about h2:first-child')).nativeElement;
    expect(heading.textContent).toContain('About Udaan');
  });

  it('should display the Mission section content', () => {
    const content = fixture.debugElement.query(By.css('.about p:last-child')).nativeElement;
    expect(content.textContent).toContain('“To provide every citizen of the India with the best service of the air travel to the extensive selection of destinations possible.”');
  });
});
