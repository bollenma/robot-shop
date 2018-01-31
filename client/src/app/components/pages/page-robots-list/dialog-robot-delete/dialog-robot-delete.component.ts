import {Component, OnInit, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-dialog-robot-delete',
  templateUrl: './dialog-robot-delete.component.html',
  styleUrls: ['./dialog-robot-delete.component.scss'],
})
export class DialogRobotDeleteComponent implements OnInit {
  
  constructor(public dialogRef: MatDialogRef<DialogRobotDeleteComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }
  
  ngOnInit() {
  }
  
  onYesClick() {
    this.dialogRef.close(true);
  }
}
