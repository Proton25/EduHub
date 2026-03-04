import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { User } from '../models/user.model';
import { Login } from '../models/login.model';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly apiUrl = `https://8080-ebbdcaaaebadcfedfdbdcbbaecbbddb.premiumproject.examly.io/api`;

  private userRoleSubject = new BehaviorSubject<string | null>(null);
  private usernameSubject = new BehaviorSubject<string | null>(null);
  private userIdSubject = new BehaviorSubject<number | null>(null);
  private emailSubject = new BehaviorSubject<string | null>(null);

  private loggedIn = false;

  constructor(private http: HttpClient) {}

  public initializeAuthState(): void {
    const token = this.getAuthToken();
    console.log('initializeAuthState - Token:', token);
    if (token && typeof token === 'string') {
      this.decodeToken(token);
    } else {
      console.error('Invalid token specified: must be a string');
      this.logout();
    }
  }


  logout(): void {
    localStorage.removeItem('authToken');
    localStorage.removeItem('authUser'); // Remove additional stored items
    localStorage.removeItem('LoginDTOObj'); // Remove additional stored items
    this.userRoleSubject.next(null);
    this.usernameSubject.next(null);
    this.userIdSubject.next(null);
    this.emailSubject.next(null); // Clear emailSubject as well
    this.loggedIn = false;
  }
  

  private decodeToken(token: string): void {
    try {
      const decoded: any = jwtDecode(token);
      console.log('Decoded Token:', decoded);
      if (decoded && decoded.sub && decoded.role) {
        this.userIdSubject.next(decoded.sub); // Use `sub` as the userId or identifier
        this.userRoleSubject.next(decoded.role);
        this.emailSubject.next(decoded.sub); // Use `sub` as the email
      } else {
        console.error('Decoded token missing expected properties');
        this.logout();
      }
    } catch (error) {
      console.error('Invalid token specified: must be a valid JWT', error);
      this.logout();
    }
  }
  
  

  register(user: User): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user).pipe(
      catchError(err => {
        console.error('Registration error:', err);
        return throwError(err);
      })
    );
  }

  getUserDetailsByEmail(email: string): Observable<User> {
    const params = new HttpParams().set('userEmail', email);
    return this.http.get<User>(`${this.apiUrl}/user/email`, { params });
  }

  getUserDetails(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/user/${userId}`);
  }


  login(login: Login): Observable<any> {
    console.log("AuthService: ", login);
    return this.http.post<Login>(this.apiUrl+"/login", login);
  }

  private setAuthToken(token: string): void {
    localStorage.setItem('authToken', token);
    console.log('Token stored in localStorage:', token);
    this.decodeToken(token);
  }

  private updateUserDetails(response: { jwtToken: string, email: string, userRole: string, userId: number, username: string }): void {
    console.log('Updating user details:', response);
    this.userRoleSubject.next(response.userRole);
    this.userIdSubject.next(response.userId);
    this.usernameSubject.next(response.username);
  }

  setUserRole(role: string): void {
    this.userRoleSubject.next(role);
  }

  getUserRole(): string | null {
    return this.userRoleSubject.value;
  }

  setUserId(userId: number): void {
    this.userIdSubject.next(userId);
  }

  setUserEmail(email: string): void{
    this.emailSubject.next(email);
  }
  
  getUserEmail(email: string): string{
    return this.emailSubject.value;
  }
  

  getAuthToken(): string | null {
    const token = localStorage.getItem('authToken');
    console.log('getAuthToken - Token:', token);
    return token;
  }

  getAuthHeaders(): HttpHeaders {
    const token = this.getAuthToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  get userRole$(): Observable<string | null> {
    return this.userRoleSubject.asObservable();
  }

  get username$(): Observable<string | null> {
    return this.usernameSubject.asObservable();
  }

  get userId$(): Observable<number | null> {
    return this.userIdSubject.asObservable();
  }

  get email$(): Observable<string | null>{
    return this.emailSubject.asObservable();
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }

  isAdmin(): boolean {
    return this.userRoleSubject.value?.toLowerCase() === 'educator';
  }

  isUser(): boolean {
    return this.userRoleSubject.value?.toLowerCase() === 'student';
  }

  isAuthenticated(): boolean {
    return !!this.getAuthToken();
  }
  getUserIdd(){
    const str=localStorage.getItem('authUser');
    const obj=JSON.parse(str);
    return obj.userId;
  }
  setLoggin(){
    this.loggedIn = true;
  }
  
}