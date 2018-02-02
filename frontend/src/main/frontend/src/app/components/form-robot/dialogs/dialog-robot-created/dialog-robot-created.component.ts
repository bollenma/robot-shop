import {Component, OnInit, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-dialog-robot-created',
  templateUrl: './dialog-robot-created.component.html',
  styleUrls: ['./dialog-robot-created.component.scss'],
})
export class DialogRobotCreatedComponent implements OnInit {
  
  constructor(public dialogRef: MatDialogRef<DialogRobotCreatedComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }
  
  ngOnInit() {
  }
  
}
