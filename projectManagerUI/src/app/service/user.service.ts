
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Urls, APIURLS } from '.././app.constants';
import { User } from '../model/user';

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
export class UserService {

    constructor(private http: HttpClient) {
    }

    create(user: User): Observable<User> {
        var url = Urls.getDomain().concat(APIURLS.createUser);
        return this.http.post<User>(url, user, { headers: httpOptions.headers });
    }

    update(user: User): Observable<User> {
        var url = Urls.getDomain().concat(APIURLS.saveUser);
        return this.http.put<User>(url, user, { headers: httpOptions.headers });
    }

    delete(user: User) {
        var url = Urls.getDomain().concat(APIURLS.deleteUser) + "/" + user.id;
        return this.http.delete(url, { headers: httpOptions.headers });
    }

    getAllUsers(): Observable<User[]> {
        var url = Urls.getDomain().concat(APIURLS.retrieveAllUsers);
        return this.http.get<User[]>(url, { headers: httpOptions.headers });
    }
}
