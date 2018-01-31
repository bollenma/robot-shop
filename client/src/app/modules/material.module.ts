import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatCardModule,
  MatListModule,
  MatToolbarModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatGridListModule,
  MatSelectModule, MatDialogModule, MatPaginatorModule,
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
    MatPaginatorModule,
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
    MatPaginatorModule,
  ],
})
export class MaterialModule {
}
