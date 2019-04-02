import { User } from './user';
import { Task } from './task';

export class Project {
    id: number = 0;
    project: string = "";
    startDate: string = "";
    endDate: string = "";
    priority: number = 0 ;
    manager: User;
    suspend: Boolean = false;
    task: Task[];
    noOfTasks: number;
}