import { Component, OnInit, Input } from '@angular/core';
import { Task } from '../model/task'
import { ActivatedRoute, Router } from '@angular/router';
import { TaskManagerService } from '../service/task.manager.service'
import { ProjectService } from '../service/project.service'
import { UserService } from '../service/user.service'
import { Project } from '../model/project'
import { User } from '../model/user'

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css']
})
export class TaskEditComponent implements OnInit {

  taskId: number;
  sub: any;
  action: String;
  task: Task;
  success: boolean = false;
  error: boolean = false;
  successMsg: string = "";
  errorMessage: string = "";
  tasks: Task[] = [];
  parentTasks: Task[] = [];
  users: User[];
  projects: Project[];

  constructor(private taskManagerService: TaskManagerService, 
    private activeRoute: ActivatedRoute, private router: Router, 
    private userService: UserService, private projectService: ProjectService) {
  }

  resetData() {
    this.task = new Task();
    this.task.project = new Project();
    this.task.parentTask = new Task();
    this.task.user = new User();
  } 

  ngOnInit() {
    this.loadUser();
    this.loadProject();
    this.task = new Task();
    this.task.project = new Project();
    this.task.parentTask = new Task();
    this.task.user = new User();
    this.sub = this.activeRoute.params.subscribe(params => {
      this.taskManagerService.getAllTask().subscribe(
        taskresult => {
          this.tasks  = taskresult;
          if (params['id']) {
            this.taskId = params['id'];
            this.action = "Update";
            this.task = this.tasks.find (t => t.id == params['id']);
            if (this.task.parentTask == null)  {
              this.task.parentTask = new Task();
            }
          } else {
            this.action = "Add Task";
          }
          this.parentTasks = this.tasks.filter(
            function(t) {
              return t.isParentTask;
            }
          )                  
        },
        error => {
          this.error=true;
          this.errorMessage = "Error occured While Loading Task";
        }
      );
    });
  }

  private loadProject() {
    this.projectService.getAllProject().subscribe(
      result => {
        this.projects = result;
      },
      error => {
        this.error = true;
        this.errorMessage = "Error occured While retriving Users";
      }
    )
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
 
  addTask() {
    this.taskManagerService.create(this.task).subscribe(
      addResult => {
        this.success = true;
        this.resetData();
        this.successMsg = "Task Successfully Deleted"
        this.ngOnInit();
      },
      error => {
        this.error=true;
        this.errorMessage = "Error occured While Adding Task";
      }
    )
  }

  updateTask() {
    this.taskManagerService.update(this.task).subscribe(
      addResult => {
        this.success = true;
      },
      error => {
        this.error=true;
        this.errorMessage = "Error occured While Adding Task";
      }
    )
    this.router.navigate(['viewTask']);
  }

  cancelEdit() {
    this.router.navigate(['viewTask']);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
