import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EducatoraddmaterialComponent } from './educatoraddmaterial.component';

describe('EducatoraddmaterialComponent', () => {
  let component: EducatoraddmaterialComponent;
  let fixture: ComponentFixture<EducatoraddmaterialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EducatoraddmaterialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EducatoraddmaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
