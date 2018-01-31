import {Component, OnInit} from '@angular/core';
import {PageEvent} from '@angular/material';
import {isUndefined} from 'util';
import {Page} from '../../../core/page.model';
import {RobotModel} from '../../../core/robot-model.model';
import {Robot} from '../../../core/robot.model';
import {RobotModelService} from '../../../services/robot-model/robot-model.service';
import {RobotService} from '../../../services/robot/robot.service';
import {StringUtils} from '../../../utils/stringUtils';

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
  previousQuery: string;
  searchExecuted = false;
  
  activeModel: string;
  
  pageEvent = new PageEvent();
  pageSizeOptions = [3, 6, 9, 12, 15];
  
  constructor(private robotService: RobotService,
              private robotModelService: RobotModelService) {
  }
  
  ngOnInit() {
    
    // Set the pager settings
    this.pageEvent.pageIndex = 0;
    this.pageEvent.pageSize = 6;
    
    // Set the active model to All models
    this.activeModel = this.ALL_MODELS;
    
    // Get all the robots
    this.browse(this.activeModel, this.pageEvent);
    
    // The selected model is "All models"
    this.activeModel = this.ALL_MODELS;
    
    // Get all the robot models
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
  
  browse(modelValue: string, pageEventParam: PageEvent) {
    this.activeModel = modelValue;
    if (isUndefined(pageEventParam)) {
      this.resetPageSettings();
    } else {
      this.pageEvent = pageEventParam;
    }
    
    // If there is a search executed, it is executed before the normal browsing
    if (this.searchExecuted) {
      this.launchSearch();
      return;
    }
    
    if (modelValue === this.ALL_MODELS) {
      this.robotService.findAllPaginated(this.pageEvent).subscribe(
        data => this.updateRobotsWithResults(data),
        error => console.log(error),
      );
    } else {
      this.robotService.findByModelPaginated(modelValue, this.pageEvent).subscribe(
        data => this.updateRobotsWithResults(data),
        error => console.log(error),
      );
    }
    
  }
  
  launchSearch() {
    
    // Start from first page if it's a new query
    const isNewSearch = StringUtils.isEmpty(this.previousQuery) || this.previousQuery !== this.query;
    if (isNewSearch) {
      this.resetPageSettings();
    }
    
    this.previousQuery = this.query;
    
    // A search has been executed if the query is not empty
    this.searchExecuted = !StringUtils.isEmpty(this.query);
    
    if (this.activeModel === this.ALL_MODELS) {
      this.robotService.search(this.query, this.pageEvent).subscribe(
        data => this.updateRobotsWithResults(data),
        error => console.log(error),
      );
    } else {
      return this.robotService.searchWithModel(this.query, this.activeModel, this.pageEvent).subscribe(
        data => this.updateRobotsWithResults(data),
        error => console.log(error),
      );
    }
  }
  
  resetPageSettings() {
    this.pageEvent.pageIndex = 0;
  }
  
  updateRobotsWithResults(robotsPage: Page<Robot>) {
    this.robots = robotsPage.content;
    this.pageEvent.length = robotsPage.totalElements;
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
  
}
