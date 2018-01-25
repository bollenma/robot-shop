import {Component, OnInit} from '@angular/core';
import {RobotModel} from '../../core/robot-model.model';
import {Robot} from '../../core/robot.model';
import {RobotModelService} from '../../services/robot-model/robot-model.service';
import {RobotService} from '../../services/robot/robot.service';
import generateId from '../../utils/generateId';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-robot',
  templateUrl: './form-robot.component.html',
  styleUrls: ['./form-robot.component.scss'],
})
export class FormRobotComponent implements OnInit {
  
  robotControl = new FormControl('', [Validators.required]);
  robot: Robot = new Robot();
  models: RobotModel[];
  
  constructor(private robotService: RobotService,
              private robotModelService: RobotModelService) {
  }
  
  ngOnInit() {
    
    this.generatePicture();
    this.robotModelService.findAll().subscribe(
      data => this.models = data,
      error => console.log(error),
    );
  }
  
  onSubmit() {
    this.robotService.create(this.robot).subscribe(
      data => {
        // TODO display success
        this.robot = new Robot();
        this.generatePicture();
      },
      error => {
        // TODO display error;
      },
    );
  }
  
  generatePicture() {
    this.robot.pictureHash = generateId(10);
  }
}
