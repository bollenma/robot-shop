import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PageRobotsListComponent} from '../components/pages/page-robots-list/page-robots-list.component';
import {PageRobotsNewComponent} from '../components/pages/page-robots-new/page-robots-new.component';

const routes: Routes = [
  {path: '', redirectTo: '/robots', pathMatch: 'full'},
  {path: 'robots', component: PageRobotsListComponent},
  {path: 'new-robot', component: PageRobotsNewComponent},

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  }
)

export class RoutingModule {
}
