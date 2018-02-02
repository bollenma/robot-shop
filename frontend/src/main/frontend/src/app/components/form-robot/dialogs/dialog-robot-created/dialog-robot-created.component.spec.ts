import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogRobotCreatedComponent } from './dialog-robot-created.component';

describe('DialogRobotCreatedComponent', () => {
  let component: DialogRobotCreatedComponent;
  let fixture: ComponentFixture<DialogRobotCreatedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogRobotCreatedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogRobotCreatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
