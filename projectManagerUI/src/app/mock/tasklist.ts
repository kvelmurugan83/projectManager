import { Task } from '../model/task'
import { Project } from '../model/project';
import { User } from '../model/user';

export let TASKS: Task[] = [
  {
    id: 1001,
    project: new Project(),
    taskName: 'First Task',
    startDate: '10/06/2018',
    endDate: '10/12/2018',
    priority: 26,
    isParentTask: false,
    parentTask: new Task(),
    end: false,
    user: new User()
  },
  {
    id: 1002,
    project: new Project(),
    taskName: 'Second Task',
    startDate: '10/10/2018',
    endDate: '10/19/2018',
    isParentTask: false,
    priority: 10,
    parentTask: new Task(),
    end: false,
    user: new User()
  },
  {
    id: 1003,
    project: new Project(),
    taskName: 'Third Task',
    startDate: '10/13/2018',
    endDate: '10/21/2018',
    priority: 16,
    isParentTask: false,
    parentTask: new Task(),
    end: false,
    user: new User()
  }
];