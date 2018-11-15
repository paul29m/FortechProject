import {Injectable} from '@angular/core';
import {Headers, Http, RequestOptions, Response, URLSearchParams} from '@angular/http';

import {Observable} from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import {Wine} from './wine.model';
import {Page} from './page.model';

@Injectable()
export class WineService {
  private wineUrl = 'http://localhost:8080/api/wine/';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {
  }

  getWines(urlNew: string,
           page: number,
           size: number,
           type?: string,
           location?: string,
           startTime?: string,
           endTime?: string,
           availableUntil?: string): Observable<Page> {
    const params: URLSearchParams = new URLSearchParams();
    if (!page) {
      page = 0;
    }
    if (!size) {
      size = 2;
    }
    params.set('page', page.toString());
    params.set('size', size.toString());
    if (type) {
      params.set('type', type);
    }
    if (location) {
      params.set('location', location);
    }
    if (startTime) {
      params.set('start_time', startTime);
    }
    if (endTime) {
      params.set('end_time', endTime);
    }
    if (availableUntil) {
      params.set('available_until', availableUntil);
    }
    const options: RequestOptions = new RequestOptions({headers: this.headers, params: params, withCredentials: true});
    return this.http.get(this.wineUrl + urlNew, options)
      .map(this.extract)
      .catch(this.handleError);
  }

  getWineById(id: number, urlNew): Observable<Wine> {
    const ur = this.wineUrl + urlNew + id;
    return this.http.get(ur, {withCredentials: true})
      .map(this.extractDataWine)
      .catch(this.handleError);
  }

  createWine(urlNew: string, wine): Observable<Wine> {
    let m = '';
    m = JSON.stringify(wine);
    console.log('URL: ', urlNew);
    console.log('Json sent: ', m);
    return this.http
      .post(urlNew, m, {withCredentials: true, headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }


  updateWine(urlNew: string, wine): Observable<Wine> {
    const m = JSON.stringify(wine);
    const url = `${this.wineUrl + urlNew}`;
    return this.http
      .put(url, m, {withCredentials: true, headers: this.headers})
      .map(this.extractDataWine)
      .catch(this.handleError);
  }

  deleteWine(urlNew: string, wine): Observable<Wine> {
    const url = `${this.wineUrl + urlNew}/${wine.id}`;
    return this.http
      .delete(url, {withCredentials: true, headers: this.headers})
      .map(() => null)
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

  private extractPersons(res: Response) {
    const body = res.json().personDtoList || {};
    return body;
  }

  private extractWines(res: Response) {
    const body = res.json().apiGatewayWineDtoList || {};
    return body;
  }

  private extractId(res: Response) {
    const body = res.json();
    return body.wineId || {};
  }

  private extract(res: Response): Page {
    const body: Page = res.json();
    return body;
  }

  private extractClass(res: Response) {
    const body = res.json();
    return body.wineClass || {};
  }

  private extractData(res: Response) {
    const body = res.json();
    return body.content || {};
  }

  private extractDataWine(res: Response) {
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

