import {Component, OnInit} from '@angular/core';
import {Robot} from '../../../core/robot.model';
import {RobotService} from '../../../services/robot/robot.service';
import {RobotModelService} from '../../../services/robot-model/robot-model.service';
import {RobotModel} from '../../../core/robot-model.model';
import {forEach} from '@angular/router/src/utils/collection';

@Component({
  selector: 'app-page-robots-list',
  templateUrl: './page-robots-list.component.html',
  styleUrls: ['./page-robots-list.component.scss'],
})
export class PageRobotsListComponent implements OnInit {
  
  readonly ALL_MODELS = 'all';
  
  robots: Robot[] = [];
  models: RobotModel[];
  modelLabels: Map<string, string> = new Map();
  
  query: string;
  activeModel: string;
  
  currentPage: number;
  maximumPage: number;
  
  constructor(private robotService: RobotService,
              private robotModelService: RobotModelService) {
  }
  
  ngOnInit() {
    
    this.browseAll();
    this.activeModel = this.ALL_MODELS;
    
    this.robotModelService.findAll().subscribe(
      data => {
        this.models = data;
        for (const model of this.models) {
          this.modelLabels.set(model.name, model.label);
        }
      },
      error => console.log(error),
    );
  }
  
  browseAll() {
    this.robotService.findAllPaginated().subscribe(
      data => this.robots = data.content,
      error => console.log(error),
    );
    this.activeModel = this.ALL_MODELS;
  }
  
  browseByModel(modelValue: string) {
    this.robotService.findByModelPaginated(modelValue).subscribe(
      data => this.robots = data.content,
      error => console.log(error),
    );
    this.activeModel = modelValue;
  }
  
  buyRobot(robot: Robot): void {
    robot.soldout = true;
    this.robotService.update(robot).subscribe(response => robot = response);
    
  }
  
  deleteRobot(id: number): void {
    this.robotService.remove(id);
    
    // Remove robot from the controller's list
    const index = this.robots.findIndex(d => d.id === id);
    this.robots.splice(index, 1);
    
  }
  
  getModelLabel(modelName: string): string {
    return this.modelLabels.get(modelName);
  }
  
  launchSearch() {
    if (this.activeModel === this.ALL_MODELS) {
      this.robotService.search(this.query).subscribe(
        data => this.robots = data.content,
        error => console.log(error),
      );
    } else {
      return this.robotService.searchWithModel(this.query, this.activeModel).subscribe(
        data => this.robots = data.content,
        error => console.log(error),
      );
    }
  }
}
