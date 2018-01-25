import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageRobotsListComponent } from './page-robots-list.component';

describe('PageRobotsListComponent', () => {
  let component: PageRobotsListComponent;
  let fixture: ComponentFixture<PageRobotsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageRobotsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageRobotsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
