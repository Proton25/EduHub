
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-usernav',
  templateUrl: './usernav.component.html',
  styleUrls: ['./usernav.component.css']
})
export class UsernavComponent implements OnInit {

  username: string | null = '';
  role: string | null = '';
  userId: number | null = null;
  useremail: string | null = '';
  dropdownOpen = false;
  showConfirmationDialog = false;
  userDetails: User = {

  };

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {

    const loginData = localStorage.getItem('authUser');
    console.log(loginData);

    const str = localStorage.getItem("authUser");
    const obj = JSON.parse(str);
    console.log("USER NAVBAR NAVBAR " + JSON.stringify(obj));
    this.role = obj.role;
    this.username = obj.username;

  }

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  confirmLogout(): void {
    this.showConfirmationDialog = true;
  }

  logout(): void {
    this.authService.logout(); // Call the logout method from AuthService
    this.showConfirmationDialog = false;
    this.router.navigate(['/home'])
  }

  cancelLogout(): void {
    this.showConfirmationDialog = false;
  }
}