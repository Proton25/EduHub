
import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs/operators';
import { Course } from 'src/app/models/course.model';
import { AuthService } from 'src/app/services/auth.service';

import { CourseService } from 'src/app/services/course.service';
import { EnrollmentService } from 'src/app/services/enrollment.service';

/**
 * This component handles the display and management of user's courses and enrollments.
 * It retrieves course and enrollment data from the respective services and provides
 * functionality to unenroll from courses.
 */
@Component({
  selector: 'app-mycourse',
  templateUrl: './mycourse.component.html',
  styleUrls: ['./mycourse.component.css']
})
export class MycourseComponent implements OnInit {
  enrollments: any[] = [];
  userId: number | null = null;

  /**
   * Constructor to inject the CourseService and EnrollmentService.
   * @param service - Service to handle course-related operations.
   * @param enrollmentService - Service to handle enrollment-related operations.
   */
  constructor(
    private service: CourseService, 
    private enrollmentService: EnrollmentService,
    private authService: AuthService
  ) { }

  /**
   * OnInit lifecycle hook to initialize the component.
   * This method is automatically called by Angular when the component is initialized.
   */
  ngOnInit(): void {
    this.userId=this.authService.getUserIdd();

     this.getEnrolledCourses();

  }

  getEnrolledCourses(): void {
    if (this.userId) {
      this.enrollmentService.getEnrolledCourses(this.userId).subscribe(data => {
        this.enrollments = data;
        this.enrollments.forEach(enrollment => {
          this.getCourseByCourseId(enrollment.courseId).then(course => {
            enrollment.courseTitle = course.title;
          });
        });
        console.log("Fetched enrollments with course titles: ", JSON.stringify(this.enrollments));
      });
    }
  }

  getCourseByCourseId(id: number): Promise<Course> {
    return new Promise((resolve) => {
      this.service.getCourseById(id).subscribe(data => {
        console.log("Fetched course data: ", JSON.stringify(data));
        resolve(data);
      });
    });
  }

  /**
   * Unenrolls the user from a course using the EnrollmentService.
   * @param id - The enrollment ID of the course to be unenrolled.
   */
  // unEnrollCourse(id: number): void {
  //   this.enrollmentService.unenrollCourse(id).subscribe(() => {
  //     console.log("Course unenrolled successfully.");
  //     this.getEnrolledCourses();
  //     // Optional: Add logic to handle UI updates or notifications after unenrollment.
  //   });
  // }

  unEnrollCourse(id: number): void {
    this.enrollmentService.unenrollCourse(id).subscribe(() => {
      console.log("Course unenrolled successfully.");
      this.enrollments = this.enrollments.filter(enrollment => enrollment.enrollmentId !== id);
      if (this.enrollments.length === 0) {
        this.enrollments = [];
      }
    });
  }
}
