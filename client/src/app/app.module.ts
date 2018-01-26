import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';
import {FormRobotComponent} from './components/form-robot/form-robot.component';
import {PageRobotsListComponent} from './components/pages/page-robots-list/page-robots-list.component';
import {PageRobotsNewComponent} from './components/pages/page-robots-new/page-robots-new.component';
import {ActiveItemDirective} from './directives/active-item.directive';
import {MaterialModule} from './modules/material.module';
import {RoutingModule} from './modules/routing.module';
import {RobotModelService} from './services/robot-model/robot-model.service';
import {RobotService} from './services/robot/robot.service';
import {DialogRobotCreatedComponent} from './components/form-robot/dialogs/dialog-robot-created/dialog-robot-created.component';
import {DialogRobotCreationErrorComponent} from './components/form-robot/dialogs/dialog-robot-creation-error/dialog-robot-creation-error.component';

@NgModule({
  declarations: [
    AppComponent,
    PageRobotsListComponent,
    PageRobotsNewComponent,
    FormRobotComponent,
    ActiveItemDirective,
    DialogRobotCreatedComponent,
    DialogRobotCreationErrorComponent,
  ],
  
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    RoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  
  providers: [
    RobotService,
    RobotModelService,
  ],
  
  bootstrap: [AppComponent],
  
  entryComponents: [
    DialogRobotCreatedComponent,
    DialogRobotCreationErrorComponent,
  ],
  
})

export class AppModule {
}
