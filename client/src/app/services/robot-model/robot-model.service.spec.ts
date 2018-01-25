import { TestBed, inject } from '@angular/core/testing';

import { RobotModelService } from './robot-model.service';

describe('RobotModelService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RobotModelService]
    });
  });

  it('should be created', inject([RobotModelService], (service: RobotModelService) => {
    expect(service).toBeTruthy();
  }));
});
