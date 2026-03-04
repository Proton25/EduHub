import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course.model';
import { CourseService } from 'src/app/services/course.service';
import { Material } from 'src/app/models/material.model';
import { MaterialService } from 'src/app/services/material.service';

@Component({
  selector: 'app-educatorviewcourse',
  templateUrl: './educatorviewcourse.component.html',
  styleUrls: ['./educatorviewcourse.component.css']
})
export class EducatorviewcourseComponent implements OnInit {
  courses: Course[] = [];
  materials: Material[] = [];
  isDeleteDialogVisible: boolean = false;
  isViewMaterialDialogVisible: boolean = false;
  selectedCourseId: number | null = null;
  selectedMaterialId: number | null = null;
  courseMaterials: Material[] = [];
  selectedCourseTitle: string = '';
  delMaterails:Material[]=[];

  constructor(private router: Router, private courseService: CourseService, private materialService: MaterialService) { }

  ngOnInit(): void {
    this.getAllCourses();
  }

  getAllCourses() {
    this.courseService.getAllCourses().subscribe((data) => {
      this.courses = data;
    });
  }

  editCourse(courseId: number) {
    this.router.navigate(['/educatoreditCourse', courseId]);
  }

  deleteCourse(courseId: number) {
    
    this.materialService.getMaterialsByCourseId(courseId).subscribe((data)=>
    {
        this.delMaterails=data;
        console.log("material data fetched "+JSON.stringify(this.delMaterails));
        for(let i=0;i<this.delMaterails.length;i++)
       {
        this.materialService.deleteMaterial(this.delMaterails[i].materialId).subscribe((data)=>
        {
            console.log("data deleted in material with courseId "+ courseId+ " of data"+ this.delMaterails[i].materialId);
        })
    }
    })
    this.selectedCourseId = courseId;
    this.isDeleteDialogVisible = true;
    this.selectedMaterialId = null; 
  }

  confirmDeleteCourse() {
    if (this.selectedCourseId !== null) {
      // Perform delete operation
      this.courseService.deleteCourse(this.selectedCourseId).subscribe(() => {
        console.log("Course deleted");
        this.getAllCourses();
      });
      this.courses = this.courses.filter(course => course.courseId !== this.selectedCourseId);
      this.isDeleteDialogVisible = false;
      this.selectedCourseId = null;
    }
  }

  cancelDeleteCourse() {
    for(let i=0;i<this.delMaterails.length;i++)
    {
      this.materialService.addMaterial(this.delMaterails[i]).subscribe((data)=>
      {
           this.materials.push(data);
      })

    }
    console.log("data in cancel delete "+JSON.stringify(this.delMaterails));
    
    this.isDeleteDialogVisible = false;
    this.selectedCourseId = null;
  }

  addMaterial(courseId: number) {
    this.router.navigate(['/educatoraddmaterial', courseId]);
  }


  viewMaterial(courseId: number) {
    this.materialService.getMaterialsByCourseId(courseId).subscribe((data: Material[]) => {
      console.log("Materials data: ", data);
      this.materials = Array.isArray(data) ? data : [];
      this.courseMaterials = this.materials.filter(material => material.courseId === courseId);
      const selectedCourse = this.courses.find(course => course.courseId === courseId);
      this.selectedCourseTitle = selectedCourse ? selectedCourse.title : '';
      this.selectedCourseId = courseId;
      this.isViewMaterialDialogVisible = true;
    }, error => {
      console.error("Error fetching materials", error);
    });
  }
   allMaterial:Material[]=[];
   getAllMaterials()
   {
    this.materialService.getAllMaterials().subscribe((data)=>
    {
        this.allMaterial=data;
    })
    
   }
 

  deleteMaterial(materialId: number) {
    this.materialService.deleteMaterial(materialId).subscribe((data)=>
    {
      console.log("material deleted"+data);
      this.getAllMaterials();
    });

    this.selectedMaterialId = materialId;
    this.isDeleteDialogVisible = true;
  }

  confirmDeleteMaterial() {
    if (this.selectedMaterialId !== null) {
      // Perform delete operation
      this.materials = this.materials.filter(material => material.materialId !== this.selectedMaterialId);
      this.courseMaterials = this.courseMaterials.filter(material => material.materialId !== this.selectedMaterialId);
      this.isDeleteDialogVisible = false;
      this.selectedMaterialId = null;
    }
  }

  cancelDeleteMaterial() {
    this.isDeleteDialogVisible = false;
    this.selectedMaterialId = null;
  }

  closeViewMaterialDialog() {
    this.isViewMaterialDialogVisible = false;
    this.selectedCourseId = null;
    this.courseMaterials = [];
    this.selectedCourseTitle = '';
  }
}



