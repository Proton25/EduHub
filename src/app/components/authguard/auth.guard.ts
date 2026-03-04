import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

 
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
 
  constructor(private readonly authService:AuthService,private route:ActivatedRoute,private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const expectedRoles = route.data['roles'];
      const userRole = localStorage.getItem('userRole');
      console.log(expectedRoles);
      console.log(this.authService.isLoggedIn());
      if (this.authService.isLoggedIn() && expectedRoles.includes(userRole)) {
        return true;
      } else {
        this.router.navigate(['/home']);
        return false;
      }
  }
 
}
 
 