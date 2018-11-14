import {PersonService} from '../shared/person.service';
import {Wine} from '../../wine/shared/wine.model';

import {ActivatedRoute, Params, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';

import 'rxjs/add/operator/switchMap';
import {Person} from '../shared/person.model';
import {GlobalApp} from '../../helpers/global';

@Component({
  selector: 'app-profile-view',
  templateUrl: './profile-view.component.html',
  styleUrls: ['./profile-view.component.scss']
})
export class ProfileViewComponent implements OnInit {


  person: Person = new Person();

  app: GlobalApp;

  wines: Wine[] = [];

  photo: string;


  private url: String = 'http://localhost:8080/api/person/';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private personService: PersonService) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.personService.getByUsername(this.url + 'getbyusername/', params['username']))
      .subscribe(person => {
        if (this.app.localStorageItem('username') === person.username) {
          this.app.setLocalStorage(person);
        }
        this.wines = person.wines.apiGatewayWineDtoList;
        this.person = person;
        this.photo = 'data:image/jpg;base64,' + person.picture;
      });
  }

  seeDetails(id: string): void {
    this.router.navigateByUrl('/wine/view/' + id);
  }

  editWine(id: string): void {
    this.router.navigate(['/wine/edit/' + id]);
  }

  turnPage(page: number): void {
    console.log(page);
  }
}
