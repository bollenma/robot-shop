import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';
import {FileUploadFormComponent} from './components/file-upload/file-upload-form/file-upload-form.component';
import {FileUploadListComponent} from './components/file-upload/file-upload-list/file-upload-list.component';
import {FormRobotComponent} from './components/form-robot/form-robot.component';
import {PageRobotsListComponent} from './components/pages/page-robots-list/page-robots-list.component';
import {PageRobotsNewComponent} from './components/pages/page-robots-new/page-robots-new.component';
import {ActiveItemDirective} from './directives/active-item.directive';
import {MaterialModule} from './modules/material.module';
import {RoutingModule} from './modules/routing.module';
import {RobotModelService} from './services/robot-model/robot-model.service';
import {RobotService} from './services/robot/robot.service';

@NgModule({
  declarations: [
    AppComponent,
    FileUploadFormComponent,
    FileUploadListComponent,
    PageRobotsListComponent,
    PageRobotsNewComponent,
    FormRobotComponent,
    ActiveItemDirective,
  ],
  
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    RoutingModule,
    FormsModule,
  ],
  
  providers: [
    RobotService,
    RobotModelService,
  ],
  bootstrap: [AppComponent],
})

export class AppModule {
}
