
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Urls, APIURLS } from '.././app.constants';
import { Project } from '../model/project';
import { ProjectBean } from '../model/projectbean'

const httpOptions = {
    headers: new HttpHeaders(
        { 
            'Content-Type': 'application/json', 
            'Access-Control-Allow-Origin': '*' ,
            'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS'
        }
    )
};

@Injectable()
export class ProjectService {

    constructor(private http: HttpClient) {
    }

    create(project: Project): Observable<Project> {
        var url = Urls.getDomain().concat(APIURLS.createProject);
        return this.http.post<Project>(url, project, { headers: httpOptions.headers });
    }

    suspendProject(project: Project) {
        project.suspend = true;    
        var url = Urls.getDomain().concat(APIURLS.saveProject);
        return this.http.put<Project>(url, project, { headers: httpOptions.headers });    
    }

    update(project: Project): Observable<Project> {
        var url = Urls.getDomain().concat(APIURLS.saveProject);
        return this.http.put<Project>(url, project, { headers: httpOptions.headers });
    }

    delete(project: Project) {
        var url = Urls.getDomain().concat(APIURLS.deleteProject) + "/" + project.id;
        return this.http.delete(url, { headers: httpOptions.headers });
    }

    getAllProject(): Observable<Project[]> {
        var url = Urls.getDomain().concat(APIURLS.retrieveAllProjects);
        return this.http.get<Project[]>(url, { headers: httpOptions.headers });
    }
}
