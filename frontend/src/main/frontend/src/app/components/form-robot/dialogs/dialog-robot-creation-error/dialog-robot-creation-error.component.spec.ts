import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogRobotCreationErrorComponent } from './dialog-robot-creation-error.component';

describe('DialogRobotCreationErrorComponent', () => {
  let component: DialogRobotCreationErrorComponent;
  let fixture: ComponentFixture<DialogRobotCreationErrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogRobotCreationErrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogRobotCreationErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
