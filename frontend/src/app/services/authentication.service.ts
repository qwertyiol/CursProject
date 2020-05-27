import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { User } from "../models/User";


@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    public enteredText: string;
    public currentUser: User;

    constructor(
        private http: HttpClient
    ) {
    }

    authenticate(username, password) {
        try {
            return this.http.get<any>(`/api/user/authenticate?username=${username}&password=${password}`).pipe(
                map(
                    userData => {
                        sessionStorage.removeItem('user');
                        sessionStorage.removeItem('token');
                        
                        sessionStorage.setItem('user', JSON.stringify(userData.user));
                        let tokenStr = 'Bearer ' + userData.token;
                        sessionStorage.setItem('token', tokenStr);
                        return userData;
                    }
                )

            );
        }
        catch {
            alert("User not found!");
        }
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem('user')
        return !(user === null)
    }

    logout() {
        sessionStorage.removeItem('user');
        sessionStorage.removeItem('token');
    }

    get currUser(): User {
        if (!this.currentUser) {
            this.currentUser = JSON.parse(sessionStorage.getItem('user'));
        }
        return this.currentUser;
    }

    getUserById(id: number): Observable<User> {
        return this.http.get<User>('/api/user/id?id=' + id);
    }

    saveUser(user: User): Observable<User> {
        return this.http.post<User>('/api/user/registration', user);
    }

}
