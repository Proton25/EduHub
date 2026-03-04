import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Material } from '../models/material.model';
import { HttpClient } from '@angular/common/http';
 
@Injectable({
  providedIn: 'root'
})
export class MaterialService {
 
  //Api based Url
 
  public apiUrl = 'https://8080-ebbdcaaaebadcfedfdbdcbbaecbbddb.premiumproject.examly.io/api/material'
 
 
 
  //Constructor to inject the HttpClient service for making Http requests
  constructor(private http: HttpClient) { }
 
  //Method to retrieve all materials by courseId
  getMaterialsByCourseId(courseId: number): Observable<Material[]> {
    return this.http.get<Material[]>(`${this.apiUrl}/course/${courseId}`)
  }
 
  //Method to add a material
  addMaterial(material: Material): Observable<Material> {
    return this.http.post<Material>(this.apiUrl, material);
  }
 
  //Method to delete a material using MaterialId
  deleteMaterial(materialId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${materialId}`);
  }

  getAllMaterials():Observable<Material[]>{
    return this.http.get<Material[]>(this.apiUrl);
  }

 
 
}
 
 
 
 
 