import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatCardModule,
  MatListModule,
  MatToolbarModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatGridListModule,
  MatSelectModule, MatDialogModule,
} from '@angular/material';

@NgModule({
  imports: [
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatProgressSpinnerModule,
    MatGridListModule,
    MatSelectModule,
    MatDialogModule,
  ],
  exports: [
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatProgressSpinnerModule,
    MatGridListModule,
    MatSelectModule,
    MatDialogModule,
  ],
})
export class MaterialModule {
}
