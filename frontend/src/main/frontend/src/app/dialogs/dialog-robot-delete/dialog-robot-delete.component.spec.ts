import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogRobotDeleteComponent } from './dialog-robot-delete.component';

describe('DialogRobotDeleteComponent', () => {
  let component: DialogRobotDeleteComponent;
  let fixture: ComponentFixture<DialogRobotDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogRobotDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogRobotDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
