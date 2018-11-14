import {Injectable} from '@angular/core';
import {Headers, Http, RequestOptions, Response} from '@angular/http';

import {Observable} from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import {Person} from './person.model';
import {NewPerson} from './newPerson.model';
import {Wine} from '../../wine/shared/wine.model';

@Injectable()
export class PersonService {
  private personsUrl = 'http://localhost:8080/api';
  private headers = new Headers({'Content-Type': 'application/json'});
  private heads2 = new Headers({'Access-Control-Allow-Origin': 'http://localhost:8080'});

  constructor(private http: Http) {
  }

  getPersons(urlNew: string): Observable<Person[]> {
    // console.log(this.http.get(this.personsUrl + urlNew,{withCredentials: true})
    //   .map(this.extractData)
    //   .catch(this.handleError));
    console.log('Inside get persons: ', this.personsUrl + urlNew);
    return this.http.get(this.personsUrl + urlNew, {withCredentials: true})
      .map(this.extractData)
      .catch(this.handleError);
  }

  getCompletedJobs(urlNew: string, name: string): Observable<Wine[]> {
    // console.log(this.http.get(this.personsUrl + urlNew,{withCredentials: true})
    //   .map(this.extractData)
    //   .catch(this.handleError));
    console.log('Inside get completed wines: ', this.personsUrl + urlNew);
    return this.http.get(this.personsUrl + urlNew + name, {withCredentials: true})
      .map(this.extractDataJobs)
      .catch(this.handleError);
  }

  getPersonsDelete(): Observable<Person[]> {
    return this.http.get(this.personsUrl, {withCredentials: true})
      .map(this.extractData)
      .catch(this.handleError);
  }

  getPerson(id: number, urlNew): Observable<Person> {
    return this.getPersons(urlNew)
      .map(persons => persons.find(person => person.id === id));
  }

  getByUsername(url: string, name: string): Observable<any> {
    return this.http
      .get(url + name, {
        withCredentials: true
      })
      .map(this.extractDataPerson)
      .catch(this.handleError);
  }

  createPerson(urlNew: string, person): Observable<NewPerson> {
    let m = '';
    m = JSON.stringify(person);
    console.log('URL: ', this.personsUrl + urlNew);
    console.log('Json sent: ', m);
    return this.http
      .post(this.personsUrl + urlNew, m, {withCredentials: true, headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

  updatePerson(urlNew: string, person): Observable<Person> {
    const m = JSON.stringify(person);
    const url = `${this.personsUrl + urlNew}/${person.id}`;
    return this.http
      .put(url, m, {withCredentials: true, headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

  updateWithFile(url, formData: FormData): Observable<any> {
    const headers = new Headers();
    headers.append('Accept', 'application/json');
    const options = new RequestOptions({
      withCredentials: true,
      headers: headers
    });
    return this.http.put(url, formData, options)
      .map(response => response)
      .catch(error => Observable.throw(error));
  }

  deletePerson(urlNew: string, person): Observable<void> {
    const url = `${this.personsUrl + urlNew}/${person.id}`;
    return this.http
      .delete(url, {withCredentials: true, headers: this.headers})
      .map(() => null)
      .catch(this.handleError);
  }

  haveCollaborated(reviewer: string, reviewed: string): Observable<any> {
    return this.http.get(this.personsUrl + '/person/collaborated/' + reviewer + '/' + reviewed, {
      withCredentials: true
    })
      .map(this.handleCollaborated)
      .catch(this.handleError);
  }

  handleCollaborated(res: Response) {
    const body = res.json();
    return body.value;
  }

  private extractId(res: Response) {
    const body = res.json();
    return body.personId || {};
  }

  private extract(res: Response) {
    const body = res.json();
    return body;
  }

  private extractClass(res: Response) {
    const body = res.json();
    return body.personClass || {};
  }

  private extractData(res: Response) {
    const body = res.json();
    return body.persons || {};
  }

  private extractDataJobs(res: Response) {
    const body = res.json();
    return body.wines || {};
  }

  private extractDataPerson(res: Response) {
    const body = res.json();
    return body || {};
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    return Observable.throw(errMsg);
  }

}

