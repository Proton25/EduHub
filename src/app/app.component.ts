import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'EduHub';
  role: string | null = null;

  constructor(private authService: AuthService) {

  }
  ngOnIt(): void {
    const loginData = localStorage.getItem('authUser');
    this.role = JSON.parse(loginData).role;
    console.log("App.component.ts: ",this.authService.isLoggedIn());
  }
  isUserLoggedIn(): boolean {
    return this.authService.isLoggedIn(); // Implement this method in AuthService
  }


}
