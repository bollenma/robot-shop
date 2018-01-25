import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormRobotComponent } from './form-robot.component';

describe('FormRobotComponent', () => {
  let component: FormRobotComponent;
  let fixture: ComponentFixture<FormRobotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormRobotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormRobotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
