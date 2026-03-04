import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchenrollment'
})
export class SearchenrollmentPipe implements PipeTransform {

  transform(enrollments:any[] , searchText:string): any {
    if(!enrollments || !searchText)
    {
      return enrollments;
    }
    searchText = searchText.toLocaleLowerCase();
    return enrollments.filter(enrollment => 
      enrollment.user.username.toLocaleLowerCase().includes(searchText)
    ); 
  }

}
