import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Enrollment } from '../models/enrollment.model';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {

  public apiUrl="https://8080-ebbdcaaaebadcfedfdbdcbbaecbbddb.premiumproject.examly.io/api/enrollment"
  constructor(private httpClient:HttpClient) { }

  getAllEnrollments():Observable<Enrollment[]>{
    return this.httpClient.get<Enrollment[]>(this.apiUrl);
  }

  addEnrollment(enrollment:Enrollment):Observable<Enrollment>{
    return this.httpClient.post<Enrollment>(this.apiUrl,enrollment);
  }

  getEnrolledCourses(userId:number):Observable<any>{
    return this.httpClient.get<any>(`${this.apiUrl}/user/${userId}`)
  }

  updateEnrollmentStatus(id:number ,enrollment:Enrollment):Observable<Enrollment>{
    return this.httpClient.put<Enrollment>(`${this.apiUrl}/${id}`,enrollment);
  }

  unenrollCourse(enrollmentId:number):Observable<void>{
    return this.httpClient.delete<void>(`${this.apiUrl}/${enrollmentId}`);
  }

  getEnrollmentById(id:number):Observable<Enrollment>{
    return this.httpClient.get<Enrollment>(`${this.apiUrl}/${id}`);
  }


}
