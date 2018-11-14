import {Injectable} from '@angular/core';
import {GlobalApp} from '../helpers/global';
import {Headers, Http, RequestOptions, Response} from '@angular/http';
import {Person} from '../profile/shared/person.model';

import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {
  app: GlobalApp;
  private loginUrl = 'http://localhost:8080/login';
  private headers = new Headers({
    'Content-Type': 'application/x-www-form-urlencoded',
    'Access-Control-Allow-Origin': 'http://localhost:4200'
  });

  constructor(private http: Http) {
    this.app = new GlobalApp();
  }

  login(username: string, password: string) {
    const requestArgs: RequestOptions = new RequestOptions({withCredentials: true, headers: this.headers});
    return this.http
      .post(this.loginUrl, 'username=' + username + '&password=' + password, requestArgs)
      .map((response: Response) => {
        const person = new Person(username);
        this.app.setLocalStorage(person);
      });
  }

  logout() {
    localStorage.removeItem('personId');
    localStorage.removeItem('username');
    localStorage.removeItem('location');
    localStorage.removeItem('firstname');
    localStorage.removeItem('lastname');
    localStorage.removeItem('description');
    localStorage.removeItem('picture');
  }
}
