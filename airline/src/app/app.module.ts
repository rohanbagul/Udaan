import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { NavbarComponent } from './navbar/navbar.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import {MatCardModule} from '@angular/material/card';
import {MatSelectModule} from '@angular/material/select'
import { FooterComponent } from './footer/footer.component';
import { BookingComponent } from './booking/booking.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { AddScheduleComponent } from './add-schedule/add-schedule.component';
import { ConfirmBookingComponent } from './confirm-booking/confirm-booking.component';
import { AdminComponent } from './admin/admin.component';
import { AdminNavbarComponent } from './admin-navbar/admin-navbar.component';
import { AddFlightComponent } from './add-flight/add-flight.component';
import { AllFlightComponent } from './all-flight/all-flight.component';
import { AllUserComponent } from './all-user/all-user.component';
import { LoginInterceptor } from './login.interceptor';
import { AddRouteComponent } from './add-route/add-route.component';
import { AllRoutesComponent } from './all-routes/all-routes.component';
import { AllScheduleComponent } from './all-schedule/all-schedule.component';
import { UpdateScheduleComponent } from './update-schedule/update-schedule.component';
import { SearchBookingComponent } from './search-booking/search-booking.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { AboutpageComponent } from './aboutpage/aboutpage.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { AllfeedbackComponent } from './allfeedback/allfeedback.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTooltipModule} from'@angular/material/tooltip';
import { ToastrModule } from 'ngx-toastr';
import { MybookingComponent } from './mybooking/mybooking.component';
import {PopoverModule} from 'ngx-bootstrap/popover'





@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    ChangepasswordComponent,
    FooterComponent,
    BookingComponent,
    
    ScheduleComponent,
         AddScheduleComponent,
         ConfirmBookingComponent,
         AdminComponent,
         AdminNavbarComponent,
         AddFlightComponent,
         AllFlightComponent,
         AllUserComponent,
         AddRouteComponent,
         AllRoutesComponent,
         AllScheduleComponent,
         UpdateScheduleComponent,
         SearchBookingComponent,
         UpdateProfileComponent,
         ErrorpageComponent,
         AboutpageComponent,
         FeedbackComponent,
         AllfeedbackComponent,
         MybookingComponent,
    
    
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatCardModule,
    MatSelectModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    PopoverModule.forRoot(),
    MatTooltipModule
  

  ],
  providers: [
{
     provide: HTTP_INTERCEPTORS,
     useClass:LoginInterceptor,
     multi:true
    // provide:HTTP_INTERCEPTORS,
    // useClass:LoginInterceptor,
    // multi:true
 }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
