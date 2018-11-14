import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class ReviewService {
  private personsUrl = 'http://localhost:8080/api/person/';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

}

