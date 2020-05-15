import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/User";

@Injectable()
export class UserService{

  public enteredText: string;

  get currUser(): User {
    return this._currUser;
  }

  set currUser(value: User) {
    this._currUser = value;
  }

  private _currUser: User;

  constructor(private http: HttpClient){}

  getUserByUsernameAndPassword(username:string, password:string): Observable<User> {
    return this.http.get<User>('/api/user/signin?username='+username+'&password='+password);
  }

  getUserById(id:number):Observable<User>{
    return this.http.get<User>('/api/user/id?id='+id);
  }

  saveUser(user:User):Observable<User>{
    return this.http.post<User>('/api/user/registration', user);
  }
}
