import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import { Course } from 'src/app/models/course.model';

@Component({
  selector: 'app-educatoreditcourse',
  templateUrl: './educatoreditcourse.component.html',
  styleUrls: ['./educatoreditcourse.component.css']
})
export class EducatoreditcourseComponent implements OnInit {
  course: Course | undefined;
  courseId: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.courseId = +params.get('courseId')!;
      this.getCourseDetails();
    });
  }

  getCourseDetails() {
    if (this.courseId !== null) {
      this.courseService.getCourseById(this.courseId).subscribe((course) => {
        this.course = course;
      });
    }
  }

  saveCourse() {
    if (this.course) {
      this.courseService.updateCourse(this.courseId , this.course).subscribe(() => {
        // Navigate back to the view page or show a success message
        this.router.navigate(['/educatorviewCourse']);
      }, error => {
        console.error('Error updating course:', error);
        // Show an error message if needed
      });
    }
  }
}

 
