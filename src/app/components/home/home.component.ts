import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  ngOnInit(): void {
   
  }
  constructor(private authService: AuthService) {

  }
  ngOnIt(): void {
    const loginData = localStorage.getItem('authUser');
    // this.role = JSON.parse(loginData).role;
    console.log("App.component.ts: ",this.authService.isLoggedIn());
  }
  isUserLoggedIn(): boolean {
    return this.authService.isLoggedIn(); // Implement this method in AuthService
  }

}
