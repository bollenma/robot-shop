import {Component, OnInit} from '@angular/core';
import {RobotService} from '../services';

@Component({
  selector: 'app-robot-list',
  templateUrl: './robot-list.component.html',
  styleUrls: ['./robot-list.component.css'],
  providers: [RobotService],
})
export class RobotListComponent implements OnInit {
  
  robots: Array<any>;
  numberOfElements: number;
  
  constructor(private robotService: RobotService) {
  }
  
  ngOnInit() {
    this.robotService.getAll().subscribe(
      data => {
        this.robots = data.content;
        this.numberOfElements = data.numberOfElements;
      },
      error => console.log(error),
    );
  }
  
}
