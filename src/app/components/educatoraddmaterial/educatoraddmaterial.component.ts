import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Material } from 'src/app/models/material.model';
import { MaterialService } from 'src/app/services/material.service';

@Component({
  selector: 'app-educatoraddmaterial',
  templateUrl: './educatoraddmaterial.component.html',
  styleUrls: ['./educatoraddmaterial.component.css']
})
export class EducatoraddmaterialComponent implements OnInit {
  materialForm: FormGroup;
  id: number;
  material: Material = {};
  loading: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private materialService: MaterialService,
    private route: ActivatedRoute,
    private router: Router
    ){
    
    this.materialForm = this.formBuilder.group({
      title: ['', Validators.required], 
      description: ['', Validators.required],  
      url: ['', Validators.required], 
      contentType: ['', Validators.required]  
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = +params.get('courseId'); 
      console.log("id fetched " + this.id); 
    });
  }

  onSubmit(): void {
    if (this.materialForm.valid) {
      this.loading = true;
      this.material.courseId = this.id;
      this.material.uploadDate = new Date();
      this.material.title = this.materialForm.value.title;
      this.material.description = this.materialForm.value.description;
      this.material.url = this.materialForm.value.url;
      this.material.contentType = this.materialForm.value.contentType;
      console.log(this.material);
      this.materialService.addMaterial(this.material).subscribe({
        next: (response) => {
          this.loading = false;  
          console.log("Material added successfully", response);  
          this.showSuccessPopup();
        },
        error: (error) => {
          this.loading = false; 
          console.log('Error adding material', error);  
        }
      });
    } else {
      console.warn('Form is not valid');  
    }
  }

  showSuccessPopup(): void {
    // alert('Material added successfully!');
    this.router.navigate(['/educatorviewCourse']);
  }

  resetForm(): void {
    this.materialForm.reset();  
  }
}
