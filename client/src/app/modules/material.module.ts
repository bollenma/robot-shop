import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatCardModule,
  MatListModule,
  MatToolbarModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatGridListModule, MatSelectModule,
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
  ],
})
export class MaterialModule {
}
