import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutpageComponent } from './aboutpage/aboutpage.component';
import { AddFlightComponent } from './add-flight/add-flight.component';
import { AddRouteComponent } from './add-route/add-route.component';
import { AddScheduleComponent } from './add-schedule/add-schedule.component';
import { AdminComponent } from './admin/admin.component';
import { AllFlightComponent } from './all-flight/all-flight.component';
import { AllRoutesComponent } from './all-routes/all-routes.component';
import { AllScheduleComponent } from './all-schedule/all-schedule.component';
import { AllUserComponent } from './all-user/all-user.component';
import { AllfeedbackComponent } from './allfeedback/allfeedback.component';
import { BookingComponent } from './booking/booking.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { ConfirmBookingComponent } from './confirm-booking/confirm-booking.component';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { AuthGuard } from './Guards/auth.guard';
import { RoleGuard } from './Guards/role.guard';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MybookingComponent } from './mybooking/mybooking.component';
import { RegisterComponent } from './register/register.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { SearchBookingComponent } from './search-booking/search-booking.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { UpdateScheduleComponent } from './update-schedule/update-schedule.component';

export const routes: Routes = [
  {path:'',redirectTo:'home',pathMatch:'full'},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'home',component:HomeComponent},
  {path:'changepassword',component:ChangepasswordComponent,canActivate:[AuthGuard]},
  {path: 'schedule', component:ScheduleComponent},
  {path:'addSchdule',component:AddScheduleComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'booking',component:BookingComponent,canActivate:[AuthGuard]},
  {path:'confirmBooking/:bookingId',component:ConfirmBookingComponent,canActivate:[AuthGuard]},
  {path:'admin',component:AdminComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'addFlight', component:AddFlightComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'allFlight',component:AllFlightComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'alluser',component:AllUserComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'addroute',component:AddRouteComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'allroutes',component:AllRoutesComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'allschedule', component:AllScheduleComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'updateschedule',component:UpdateScheduleComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'searchbooking/:bookingId',component:SearchBookingComponent,canActivate:[AuthGuard]},
  {path:'updateprofile/:id',component:UpdateProfileComponent,canActivate:[AuthGuard]},
  {path:'feedback',component:FeedbackComponent, canActivate:[AuthGuard]},
  {path:'aboutpage',component:AboutpageComponent},
  {path:'allfeedback',component:AllfeedbackComponent},
  {path:'mybooking/:id',component: MybookingComponent,canActivate:[AuthGuard]},
  {path:'**',component:ErrorpageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
