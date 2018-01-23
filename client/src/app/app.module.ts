import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';
import {MaterialModule} from './material.module';
import {RobotListComponent} from './robot-list/robot-list.component';

@NgModule({
  declarations: [
    AppComponent,
    RobotListComponent,
  ],
  
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
  ],
  
  providers: [],
  bootstrap: [AppComponent],
})

export class AppModule {
}
