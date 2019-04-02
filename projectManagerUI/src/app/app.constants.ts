import { environment } from "../environments/environment";

const URLS = {
    local: "http://localhost:8001",
    prod: ""
}

export class Urls {
    public static getDomain() {
        if (environment.production) {
            return URLS.prod;
        }
        return URLS.local;
    }
}

export const APIURLS = {
    saveTask: "/projectmanager/api/task",
    createTask: "/projectmanager/api/task",
    deleteTask: "/projectmanager/api/task",
    retrieveAllTasks: "/projectmanager/api/task",
    saveUser: "/projectmanager/api/user",
    createUser: "/projectmanager/api/user",
    deleteUser: "/projectmanager/api/user",
    retrieveAllUsers: "/projectmanager/api/user",
    saveProject: "/projectmanager/api/project",
    createProject: "/projectmanager/api/project",
    deleteProject: "/projectmanager/api/project",
    retrieveAllProjects: "/projectmanager/api/project"
}
