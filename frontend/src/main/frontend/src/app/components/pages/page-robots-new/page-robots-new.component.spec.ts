import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageRobotsNewComponent } from './page-robots-new.component';

describe('PageRobotsNewComponent', () => {
  let component: PageRobotsNewComponent;
  let fixture: ComponentFixture<PageRobotsNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageRobotsNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageRobotsNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
