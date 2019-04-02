import { Component, OnInit } from '@angular/core';
import { Task } from '../model/task'
import { Router } from '@angular/router';
import { TaskManagerService } from '../service/task.manager.service'
import * as _ from 'lodash';


@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  error: boolean = false;
  errorMessage: string = "";
  success: boolean = false;
  successMsg: string = "";
  tasks: Task[];
  tasksMaster: Task[];
  taskName: string;
  parentTaskName: string;
  priorityFrom: number;
  priorityTo: number;
  dateFrom: string;
  dateTo: String;
  search: string = "";

  filters = {}

  constructor(private router: Router, private taskManagerService: TaskManagerService) {
  }

  ngOnInit() {
   this.init();
  }

  private init() {
    this.taskManagerService.getAllTask().subscribe(
      taskresult => {
        this.tasksMaster = taskresult;
        this.tasks = this.tasksMaster;
        console.log(this.tasks);
      },
      error => {
        this.error=true;
        this.errorMessage = "Error occured While retriving Tasking";
      }
    )
  }

  sort(prop: string) {
    this.tasks = this.tasks.sort((a, b) => a[prop] > b[prop] ? 1 : a[prop] === b[prop] ? 0 : -1);
  }

  private applyFilters() {
    this.tasks = _.filter(this.tasksMaster, _.conforms(this.filters))
  }

  filterContains(value: string) {
    this.tasks = this.tasksMaster.filter(
      function(task) {
        return task.project.project.includes(value);
      }
    );
  }

  editTask(task: Task) {
    this.router.navigate(['editTask/' + task.id]);
  }

  delete(task: Task) {
    this.taskManagerService.delete(task).subscribe(
      addResult => {
        this.success = true;
        this.init();
        this.successMsg = "Task Successfully Deleted"
      },
      error => {
        this.error=true;
        this.errorMessage = "Error occured While Deleting Task";
      }
    )
  }

  endTask(task: Task) {
    this.taskManagerService.endTask(task).subscribe(
      addResult => {
        this.success = true;
        this.init();
        this.successMsg = "Task Successfully Ended"
      },
      error => {
        this.error=true;
        this.errorMessage = "Error occured While Eding Task";
      }
    )
  }

}
