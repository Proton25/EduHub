import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';
 
@Injectable({
  providedIn: 'root'
})
export class CourseService {
 
  // API endpoint for course-related operations
  public apiUrl: string = "https://8080-ebbdcaaaebadcfedfdbdcbbaecbbddb.premiumproject.examly.io/api/course"
 
  constructor(private http: HttpClient) { }
 
   /**
   * Fetches all courses.
   * @returns Observable of an array of Course objects.
   */
  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl);
  }
 
  /**
   * Fetches a course by its ID.
   * @param id - ID of the course to fetch.
   * @returns Observable of a Course object.
   */
  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`)
  }
 
  /**
   * Adds a new course.
   * @param requestObject - Course object to be added.
   * @returns Observable of the added Course object.
   */
  addCourse(requestObject: Course): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, requestObject)
  }
 
  /**
   * Updates an existing course.
   * @param id - ID of the course to update.
   * @param requestObject - Updated Course object.
   * @returns Observable of the updated Course object.
   */
  updateCourse(id: number, requestObject: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${id}`, requestObject)
 
  }
 
  /**
   * Deletes a course by its ID.
   * @param courseId - ID of the course to delete.
   * @returns Observable of void.
   */
  deleteCourse(courseId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${courseId}`)
 
  }
 
}
 
 