import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  scrollPosition: number =0;
  threshold: number = 100;

  @HostListener('window:scroll', ['$event'])
  onScroll() {
    this.scrollPosition = window.scrollY;
  }

}
