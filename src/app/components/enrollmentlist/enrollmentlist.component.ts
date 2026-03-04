import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Enrollment } from 'src/app/models/enrollment.model';
import { AuthService } from 'src/app/services/auth.service';

import { CourseService } from 'src/app/services/course.service';
import { EnrollmentService } from 'src/app/services/enrollment.service';


@Component({
  selector: 'app-enrollmentlist',
  templateUrl: './enrollmentlist.component.html',
  styleUrls: ['./enrollmentlist.component.css']
})
export class EnrollmentlistComponent implements OnInit {

  enrollments: any[] = [];
  filteredEnrollments: any[] = [];
  searchText: string = '';
  filterStatus: string = 'All';
  selectedEnrollment: Enrollment = {};

  constructor(
    private enrollmentService: EnrollmentService,
    private userService: AuthService,
    private courseService: CourseService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getAllEnrollments();
  }

  getAllEnrollments(): void {
    this.enrollmentService.getAllEnrollments().subscribe(data => {
      this.enrollments = data;
      this.enrollments.forEach(enrollment => {
        this.getUserDetails(enrollment.userId).then(user => {
          enrollment.user = user;
          this.getCourseDetails(enrollment.courseId).then(course => {
            enrollment.course = course;
            this.updateFilteredEnrollments();
          });
        });
      });
      console.log("Data enrolled: ", this.enrollments);
    },
    error => {
      console.error('Error fetching all enrollments', error);
    });
  }

  getUserDetails(userId: number): Promise<any> {
    return new Promise((resolve) => {
      this.userService.getUserDetails(userId).subscribe(data => {
        console.log("Fetched user details: ", data);
        resolve(data);
      });
    });
  }

  getCourseDetails(courseId: number): Promise<any> {
    return new Promise((resolve) => {
      this.courseService.getCourseById(courseId).subscribe(data => {
        console.log("Fetched course details: ", data);
        resolve(data);
      });
    });
  }

  approve(enrollment: Enrollment): void {
    enrollment.status = 'approved';
    this.enrollmentService.updateEnrollmentStatus(enrollment.enrollmentId, enrollment).subscribe(() => {
      console.log('Enrollment Approved: ', enrollment);
      this.getAllEnrollments();
    },
    error => {
      console.error('Error Approving enrollment', error);
    });
  }

  reject(enrollment: Enrollment): void {
    enrollment.status = 'rejected';
    this.enrollmentService.updateEnrollmentStatus(enrollment.enrollmentId, enrollment).subscribe(() => {
      console.log('Enrollment Rejected: ', enrollment);
      this.getAllEnrollments();
    },
    error => {
      console.error('Error Rejecting enrollment', error);
    });
  }

  showMore(enrollment: Enrollment): void {
    this.selectedEnrollment = enrollment;
    this.router.navigate(['/educatorviewCourse']);
  }

  closeModal(): void {
    this.selectedEnrollment = null;
  }

  updateFilteredEnrollments(): void {
    let f = this.filterStatus.toLowerCase();
    this.filteredEnrollments = this.enrollments.filter(enrollment => {
      const matchesStatus = f === 'all' || enrollment.status.toLowerCase() === f;
      const matchesSearchText = this.searchText.length === 0 || enrollment.user.username.toLowerCase().includes(this.searchText.toLowerCase());
      return matchesStatus && matchesSearchText;
    });
  }
}

