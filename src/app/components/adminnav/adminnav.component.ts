import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
 
@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {
  username: string;
  role: string | null = '';
  userId:number;
  dropdownOpen = false;
  showConfirmationDialog = false;
  email: string | null = '';
 
  userDetails: User = {};
 
  constructor(private authService: AuthService, private router: Router) {}
 
  ngOnInit(): void {
    const str=localStorage.getItem("authUser");
    const obj=JSON.parse(str);
    console.log("ADMIN NAVBAR "+JSON.stringify(obj));
    this.role=obj.role;
    this.username=obj.username;
  }
  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }
 
  confirmLogout(): void {
    this.showConfirmationDialog = true;
  }
 
  logout(): void {
    this.authService.logout();
    this.showConfirmationDialog = false;
    this.router.navigate(['/home']);
  }
 
  cancelLogout(): void {
    this.showConfirmationDialog = false;
  }
}
 
 