import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { EducatorviewcourseComponent } from './components/educatorviewcourse/educatorviewcourse.component';
import { EducatoreditcourseComponent } from './components/educatoreditcourse/educatoreditcourse.component';
import { EducatoraddcourseComponent } from './components/educatoraddcourse/educatoraddcourse.component';
import { EducatoraddmaterialComponent } from './components/educatoraddmaterial/educatoraddmaterial.component';
import { EnrollmentlistComponent } from './components/enrollmentlist/enrollmentlist.component';
import { StudentviewcourseComponent } from './components/studentviewcourse/studentviewcourse.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { MycourseComponent } from './components/mycourse/mycourse.component';

import { ErrorComponent } from './components/error/error.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { UserHomeComponent } from './components/user-home/user-home.component';
import { AuthGuard } from './components/authguard/auth.guard';

const routes: Routes = [
  { path: "", redirectTo: "/home", pathMatch: "full" },
  { path: "home", component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'educatorviewCourse', component: EducatorviewcourseComponent,canActivate:[AuthGuard] , data :{roles :['EDUCATOR']}},
  { path: 'educatoraddcourse', component: EducatoraddcourseComponent ,canActivate:[AuthGuard] , data :{roles :['EDUCATOR']}},
  { path: 'educatoreditCourse/:courseId', component: EducatoreditcourseComponent ,canActivate:[AuthGuard] , data :{roles :['EDUCATOR']}},
  { path: 'educatoraddmaterial/:courseId', component: EducatoraddmaterialComponent ,canActivate:[AuthGuard] , data :{roles :['EDUCATOR']} },
  { path: 'admin-home', component: AdminHomeComponent ,canActivate:[AuthGuard] , data :{roles :['EDUCATOR']} },
  { path: "adminnav", component: AdminnavComponent,canActivate:[AuthGuard] , data :{roles :['EDUCATOR']} },
  { path: 'enrollmentList', component: EnrollmentlistComponent ,canActivate:[AuthGuard] , data :{roles :['EDUCATOR']} },
  { path: 'adminviewfeedback', component: AdminviewfeedbackComponent,canActivate:[AuthGuard] , data :{roles :['EDUCATOR']} },
  { path: "usernav", component: UsernavComponent ,canActivate:[AuthGuard] , data :{roles :['STUDENT']}},
  { path: "navbar", component: NavbarComponent },
  { path: 'studentviewCourse', component: StudentviewcourseComponent ,canActivate:[AuthGuard] , data :{roles :['STUDENT']} },
  { path: 'studentviewcourse', component: StudentviewcourseComponent ,canActivate:[AuthGuard] , data :{roles :['STUDENT']}},
  { path: 'useraddfeedback', component: UseraddfeedbackComponent ,canActivate:[AuthGuard] , data :{roles :['STUDENT']} },
  { path: 'userviewfeedback', component: UserviewfeedbackComponent ,canActivate:[AuthGuard] , data :{roles :['STUDENT']}},
  { path: 'mycourse', component: MycourseComponent ,canActivate:[AuthGuard] , data :{roles :['STUDENT']}},
  { path: 'user-home', component: UserHomeComponent ,canActivate:[AuthGuard] , data :{roles :['STUDENT']}},
 

  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }