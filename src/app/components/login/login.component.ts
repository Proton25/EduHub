


import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/app/models/login.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
   email: string = '';
  password: string = '';
  role: string = '';

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void { }

  login(): void {
    const loginData: Login = { email: this.email, password: this.password };
    console.log('Login data:', loginData);
    this.authService.login(loginData).subscribe(
      response => {
        console.log('LOGINNNNNN :', JSON.stringify(response));
        // alert('Login successful!');
        this.authService.setLoggin();
        localStorage.setItem("authUser", JSON.stringify(response));
        // console.log(response);
        // console.log(response.jwtToken);
        localStorage.setItem("jwtToken",response.jwtToken);
        localStorage.setItem("userRole",response.role);
        // localStorage.setItem("token",response);
        // this.authService.getUserDetailsByEmail(loginData.email).subscribe((data)=>
        // {
        //   console.log(data);
        // })
        this.authService.setUserRole(response.role); // Set user role from the response
        this.redirectBasedOnRole();
      },
      error => {
        console.error('Login failed:', error);
      }
    );
  }

  private redirectBasedOnRole(): void {
    this.authService.userRole$.subscribe(role => {
      this.role = role;
      console.error(this.role);
      if (this.role.toLowerCase() === 'educator') {
        console.log('admin');
        this.router.navigate(['/admin-home']);
      } else if (this.role.toLowerCase() === 'student') {
        console.log('student');
        this.router.navigate(['/user-home']);
      } else {
        console.log('noooo');
        this.router.navigate(['']);
      }
    });
  }
}