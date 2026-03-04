import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
 
@Component({
  selector: 'app-educatoraddcourse',
  templateUrl: './educatoraddcourse.component.html',
  styleUrls: ['./educatoraddcourse.component.css']
})
export class EducatoraddcourseComponent implements OnInit {
 
  addCourseForm: FormGroup;
 
  constructor(private service: CourseService, private fb: FormBuilder , private router:Router) { }
 
  ngOnInit(): void {
    this.addCourseForm = this.fb.group({
      title: ["", Validators.required],
      description: ["", Validators.required],
      courseStartDate: ["", Validators.required],
      courseEndDate: ["", Validators.required],
      category: ["", Validators.required],
      level: ["", Validators.required]
    });
  }
 
  get f() {
    return this.addCourseForm.controls;
  }
 
  onSubmit() {
    if (this.addCourseForm.valid) {
      this.service.addCourse(this.addCourseForm.value).subscribe((data) => {
        this.router.navigate(['/educatorviewCourse'])
    
        console.log("data added"+data);
        this.showSuccessModal();
      });
    }
  }
 
  showSuccessModal() {
    const modal = document.getElementById('successModal') as HTMLDialogElement;
    if (modal) {
      modal.showModal();
      setTimeout(() => {
        modal.close();
      }, 2000); 
    }
  }
 
  closeModal() {
    const modal = document.getElementById('successModal') as HTMLDialogElement;
    if (modal) {
      modal.close();
    }
  }
}