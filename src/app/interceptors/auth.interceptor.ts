import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // If the request URL includes '/login', allow the request to proceed without modification
    if (request.url.includes('/login')) {
      return next.handle(request);
    }
    // Retrieve the token from local storage
    let token = localStorage.getItem('jwtToken'); 
    // console.log(token); 
    // token = "eyJhbGciOiJIUzM4NCJ9.eyJyb2xlIjoiRURVQ0FUT1IiLCJzdWIiOiJwYXZhbkBnbWFpbC5jb20iLCJpYXQiOjE3NDAzMzk5ODUsImV4cCI6MTc0MDQyNjM4NX0.lbspyLJMyn7Dxqa8vBK6HoRECCI8_B5fSTbHB4jp0xLUhXgSfb1bEYc4AsS-1DXX"

    // If the token exists, clone the request and set the Authorization header with the token
    if (token) {
      const authreq = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      console.log(authreq);
      // Allow the modified request to proceed
      return next.handle(authreq);
    }
    // If no token is found, allow the original request to proceed without modification
    return next.handle(request);
  }
}
  