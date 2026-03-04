import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchcourse'
})
export class SearchcoursePipe implements PipeTransform {

  transform(courses:any[] , searchText:string): any {
    if(!courses)
    return [];

    if(!searchText){
      return courses;
    }

    searchText = searchText.toLocaleLowerCase();
    return courses.filter(course => 
      course.title.toLocaleLowerCase().includes(searchText)
    );

    
  }

}
