import { Project } from './project';
import { User } from './user';

export class Task {
    id: number ;
    project: Project;
    taskName: string = "";
    startDate: string = "";
    endDate: string = "";
    priority: number;
    parentTask: Task;
    isParentTask: boolean;
    end: boolean = false;
    user: User;
    
}