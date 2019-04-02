import { Component, OnInit } from '@angular/core';
import { Project } from '../model/project'
import { User } from '../model/user'
import { Router } from '@angular/router';
import { ProjectService } from '../service/project.service'
import { UserService } from '../service/user.service'
import * as _ from 'lodash';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  error: boolean = false;
  errorMessage: string = "";
  success: boolean = false;
  successMsg: string = "";
  projects: Project[];
  // projectsMaster: Project[];
  project: Project;
  enableDate: boolean = false;
  users: User[];
  search: string = "";
  sortBy: string = "userId";


  filters = {}

  constructor(private router: Router, private projectService: ProjectService, private userService: UserService) { }

  ngOnInit() {
    this.init();
    this.loadUser();
  }

  private init() {
    this.project = new Project();
    this.project.manager = new User();
    this.projectService.getAllProject().subscribe(
      result => {
        this.projects = result;
        // console.log(this.projects)
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While retriving Projects";
      }
    )
  }

  sort(prop: string) {
    this.projects = this.projects.sort((a, b) => a[prop] > b[prop] ? 1 : a[prop] === b[prop] ? 0 : -1);
  }

  reset() {
    this.project = new Project();
    this.project.manager = new User();
    this.enableDate = false;
  } 

  private loadUser() {
    this.userService.getAllUsers().subscribe(
      result => {
        this.users = result;
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While retriving Users";
      }
    )
  }

  delete(project: Project) {
    this.projectService.delete(project).subscribe(
      addResult => {
        this.success = true;
        this.init();
        this.successMsg = "Project Successfully Deleted"
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While Deleting Project";
      }
    )
  }

  add() {
    this.projectService.create(this.project).subscribe(
      addResult => {
        this.success = true;
        this.init();
        this.successMsg = "Project Successfully Added"
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While Adding Project";
      }
    )
  }

  suspend(project: Project) {
    this.project = project;
    this.project.suspend = true;
    this.update();
  }

  edit(project: Project) {
    this.project = project;
    if (this.project.startDate != null ) {
      this.enableDate = true;
    } else {
      this.enableDate = false;
    }
  }

  update() {
    this.projectService.update(this.project).subscribe(
      addResult => {
        this.success = true;
        this.init();
        this.successMsg = "Project Successfully Updated"
        this.enableDate = false;
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While Updating Project";
      }
    )
  }

}
