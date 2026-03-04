
import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/models/course.model';
import { Enrollment } from 'src/app/models/enrollment.model';
import { Material } from 'src/app/models/material.model';

import { CourseService } from 'src/app/services/course.service';
import { EnrollmentService } from 'src/app/services/enrollment.service';
import { MaterialService } from 'src/app/services/material.service';

import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-studentviewcourse',
  templateUrl: './studentviewcourse.component.html',
  styleUrls: ['./studentviewcourse.component.css']
})
export class StudentviewcourseComponent implements OnInit {

  searchText: string = '';
  isDialogVisible: boolean = false;
  success: boolean = false;
  selectedCourse: Course | null = null;
  materialDialogVisible: boolean = false;
  courseMaterials: Material[] = [];
  selectedCourseId: number | null = null;
  selectedMaterialId: number | null = null;
  selectedCourseTitle: string = '';
  courses: Course[] = [];
  materials: Material[] = [];
  enrollmentData: Enrollment = {};
  userId: number | null = null;
  enrolledCourses:any[]=[];
  enrollmentsByUser:Enrollment[]=[];

  constructor(
    private courseService: CourseService,
    private materialService: MaterialService,
    private enrollmentService: EnrollmentService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.userId=this.authService.getUserIdd();
    this.getAllCourses();
    this.getAllEnrollmentsByUserId();
    
  }

  getAllCourses() {
    this.courseService.getAllCourses().subscribe(data => {
      console.log("courses " + data);
      this.courses = data;
    });
  }

  getAllEnrollmentsByUserId()
  {
    this.enrollmentService.getEnrolledCourses(this.userId).subscribe((data)=>
    {
      this.enrollmentsByUser=data;
      console.log("enrollments by user "+this.userId+" are :"+JSON.stringify(this.enrollmentsByUser));
      this.add();
    })
    
  }

  add(){
    for(let i=0;i<this.enrollmentsByUser.length;i++){
       this.enrolledCourses[i]=this.enrollmentsByUser[i].courseId;
    }
    console.log(this.enrolledCourses);
  }
  

  enroll(courseId: number) {
    this.selectedCourseId = courseId;
    this.selectedCourse = this.courses.find(course => course.courseId === courseId) || null;
    this.isDialogVisible = true;
  }

  yesEnroll() {
    if (this.userId !== null && this.selectedCourseId !== null && this.selectedCourse !== null) {
      this.enrollmentData.courseId = this.selectedCourseId;
      this.enrollmentData.userId =this.userId;
      this.enrollmentData.enrollmentDate = new Date();
      this.enrollmentData.status = "pending";
      this.enrollmentService.addEnrollment(this.enrollmentData).subscribe(data => {
        console.log("data enrolled " + data);
        this.enrolledCourses.push(this.selectedCourse.courseId);
        console.log(this.enrolledCourses);
        this.isDialogVisible = false;
        this.success = true;
        this.getAllCourses();
      }, error => {
        console.error("Error enrolling", error);
        alert("Error enrolling in course. Please try again.");
        this.isDialogVisible = false;
      });
    } else {
      console.error("userId, selectedCourseId, or selectedCourse is null");
      alert("Error: User ID, Course ID, or selected Course is missing. Please try again.");
      this.isDialogVisible = false;
    }
  }

  closeDialog() {
    this.isDialogVisible = false;
    this.selectedCourse = null;
    this.success = false;
  }

  viewMaterial(courseId: number) {
    this.materialService.getMaterialsByCourseId(courseId).subscribe((data: Material[]) => {
      console.log("Materials data: ", data);
      this.materials = Array.isArray(data) ? data : [];
      this.courseMaterials = this.materials.filter(material => material.courseId === courseId);
      const selectedCourse = this.courses.find(course => course.courseId === courseId);
      this.selectedCourseTitle = selectedCourse ? selectedCourse.title : '';
      this.selectedCourseId = courseId;
      this.materialDialogVisible = true;
    }, error => {
      console.error("Error fetching materials", error);
    });
  }

  closeMaterialDialog() {
    this.materialDialogVisible = false;
  }
}
