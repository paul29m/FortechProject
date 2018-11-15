import {Injectable} from '@angular/core';
import {Headers, Http, RequestOptions, Response} from '@angular/http';

import {Observable} from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import {WineType} from './winetype.model';

@Injectable()
export class WinetypeService {
  private wineTypesUrl = 'http://localhost:8080/api/winetype';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

  getAll(): Observable<WineType[]> {
    const options: RequestOptions = new RequestOptions({headers: this.headers, withCredentials: true});
    return this.http.get(this.wineTypesUrl + '/getall', options)
      .map(this.extract)
      .catch(this.handleError);
  }

  private extract(res: Response): WineType[] {
    const body: WineType[] = [];
    const responseJson = res.json().apiGatewayWineTypeDtoList;
    for (let i = 0; i < responseJson.length; i++) {
      const wineType = new WineType(responseJson[i].id, responseJson[i].name, responseJson[i].wines.apiGatewayWineDtoList);
      body.push(wineType);
    }
    return body;
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
