import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { EducatoraddcourseComponent } from './components/educatoraddcourse/educatoraddcourse.component';
import { EducatoraddmaterialComponent } from './components/educatoraddmaterial/educatoraddmaterial.component';

import { EducatorviewcourseComponent } from './components/educatorviewcourse/educatorviewcourse.component';
import { EnrollmentlistComponent } from './components/enrollmentlist/enrollmentlist.component';
import { ErrorComponent } from './components/error/error.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MycourseComponent } from './components/mycourse/mycourse.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { StudentviewcourseComponent } from './components/studentviewcourse/studentviewcourse.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { SearchenrollmentPipe } from './pipes/searchenrollment.pipe';
import { SearchcoursePipe } from './pipes/searchcourse.pipe';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UserHomeComponent } from './components/user-home/user-home.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AuthGuard } from './components/authguard/auth.guard';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { EducatoreditcourseComponent } from './components/educatoreditcourse/educatoreditcourse.component';


@NgModule({
  declarations: [
    AppComponent,
    AdminnavComponent,
    AdminviewfeedbackComponent,
    EducatoraddcourseComponent,
    EducatoraddmaterialComponent,
    EducatoreditcourseComponent,
    EducatorviewcourseComponent,
    EnrollmentlistComponent,
    ErrorComponent,
    HomeComponent,
    LoginComponent,
    MycourseComponent,
    NavbarComponent,
    RegistrationComponent,
    StudentviewcourseComponent,
    UseraddfeedbackComponent,
    UsernavComponent,
    UserviewfeedbackComponent,
    SearchenrollmentPipe,
    SearchcoursePipe,
    UserHomeComponent,
    AdminHomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule,
    CommonModule  
  ],
  providers: [AuthGuard,{provide:HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
