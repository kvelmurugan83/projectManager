import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';


import { AppComponent } from './app.component';
import { TaskListComponent } from './task-list/task-list.component';
import { TaskEditComponent } from './task-edit/task-edit.component';
import { HeaderComponent } from './header/header.component';
import { TaskService } from './model/taskservice'
import { TaskManagerService } from './service/task.manager.service';
import { ProjectComponent } from './project/project.component';
import { UserComponent } from './user/user.component'
import { ProjectService } from './service/project.service';
import { UserService } from './service/user.service';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

const routes: Routes = [
  { path: '', redirectTo: '/user', pathMatch: 'full' },
  { path: 'viewTask', component: TaskListComponent },
  { path: 'addTask', component: TaskEditComponent },
  { path: 'editTask/:id', component: TaskEditComponent },
  { path: 'user', component: UserComponent },
  { path: 'project', component: ProjectComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    TaskListComponent,
    TaskEditComponent,
    HeaderComponent,
    ProjectComponent,
    UserComponent
  ],
  imports: [
    RouterModule.forRoot(
      routes,
      { enableTracing: false } // <-- debugging purposes only
    ),
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    Ng2SearchPipeModule
  ],
  providers: [TaskManagerService, TaskService, ProjectService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
