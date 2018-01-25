import {Component, OnInit} from '@angular/core';
import {Robot} from '../../../core/robot.model';
import {RobotService} from '../../../services/robot/robot.service';
import {RobotModelService} from '../../../services/robot-model/robot-model.service';
import {RobotModel} from '../../../core/robot-model.model';

@Component({
  selector: 'app-page-robots-list',
  templateUrl: './page-robots-list.component.html',
  styleUrls: ['./page-robots-list.component.scss'],
})
export class PageRobotsListComponent implements OnInit {
  
  robots: Robot[] = [];
  models: RobotModel[];
  
  activeModel = 'all';
  
  currentPage: number;
  maximumPage: number;
  
  constructor(private robotService: RobotService,
              private robotModelService: RobotModelService) {
  }
  
  ngOnInit() {
    
    this.browseAll();
    
    this.robotModelService.findAll().subscribe(
      data => this.models = data,
      error => console.log(error),
    );
  }
  
  browseAll() {
    this.robotService.findAllPaginated().subscribe(
      data => this.robots = data.content,
      error => console.log(error),
    );
    this.activeModel = 'all';
  }
  
  browseByModel(modelValue: string) {
    this.robotService.findByModelPaginated(modelValue).subscribe(
      data => this.robots = data.content,
      error => console.log(error),
    );
    this.activeModel = modelValue;
  }
  
  buyRobot(robot: Robot) {
    robot.soldout = true;
    this.robotService.update(robot).subscribe(response => robot = response);
    
  }
  
  deleteRobot(id: number) {
    this.robotService.remove(id);
    
    // Remove robot from the controller's list
    const index = this.robots.findIndex(d => d.id === id);
    this.robots.splice(index, 1);
    
  }
}
