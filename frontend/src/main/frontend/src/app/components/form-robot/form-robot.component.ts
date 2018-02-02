import {Component, OnInit} from '@angular/core';
import {Validators, FormGroup, FormBuilder} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {RobotModel} from '../../core/robot-model.model';
import {Robot} from '../../core/robot.model';
import {RobotModelService} from '../../services/robot-model/robot-model.service';
import {RobotService} from '../../services/robot/robot.service';
import generateId from '../../utils/generateId';
import {DialogRobotCreatedComponent} from './dialogs/dialog-robot-created/dialog-robot-created.component';
import {DialogRobotCreationErrorComponent} from './dialogs/dialog-robot-creation-error/dialog-robot-creation-error.component';

@Component({
  selector: 'app-form-robot',
  templateUrl: './form-robot.component.html',
  styleUrls: ['./form-robot.component.scss'],
})
export class FormRobotComponent implements OnInit {
  
  robotForm: FormGroup;
  
  robot: Robot = new Robot();
  models: RobotModel[];
  
  hashCharNumber = 10;
  minPrice = 100;
  maxPrice = 1000000;
  
  constructor(private robotService: RobotService,
              private robotModelService: RobotModelService,
              private formBuilder: FormBuilder,
              private dialog: MatDialog) {
  }
  
  ngOnInit() {
    this.createForm();
    this.robotModelService.findAll().subscribe(
      data => this.models = data,
      error => console.log(error),
    );
  }
  
  createForm() {
    const pictureHash = generateId(this.hashCharNumber);
    this.robotForm = this.formBuilder.group({
      name: ['', Validators.required],
      model: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(this.minPrice), Validators.max(this.maxPrice)]],
      pictureHash: [pictureHash, Validators.required],
    });
  }
  
  onSubmit() {
    this.robot = this.prepareSaveRobot();
    
    this.robotService.create(this.robot).subscribe(
      data => {
        this.openDialogSuccess(data);
        const pictureHash = generateId(this.hashCharNumber);
        this.robotForm.reset({
          name: '',
          model: '',
          price: '',
          pictureHash: pictureHash,
        });
      },
      error => {
        console.log(error);
        this.openDialogError();
      },
    );
  }
  
  prepareSaveRobot(): Robot {
    const robotModel = this.robotForm.value;
    const ret: Robot = {
      id: null,
      name: robotModel.name,
      model: robotModel.model,
      price: robotModel.price,
      pictureHash: robotModel.pictureHash,
      soldout: false,
      
    };
    return ret;
  }
  
  generatePicture() {
    const pictureHash = generateId(this.hashCharNumber);
    this.robotForm.patchValue({
      'pictureHash': pictureHash,
    })
    ;
  }
  
  openDialogSuccess(robot: Robot): void {
    this.dialog.open(DialogRobotCreatedComponent, {
      width: '400px',
      data: robot.name,
    });
  }
  
  openDialogError(): void {
    this.dialog.open(DialogRobotCreationErrorComponent, {
      width: '400px',
    });
  }
  
}
