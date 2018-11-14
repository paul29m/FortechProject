import {PersonService} from '../shared/person.service';
import {Wine} from '../../wine/shared/wine.model';

import {ActivatedRoute, Router} from '@angular/router';
import {Component, Input, OnInit} from '@angular/core';

import 'rxjs/add/operator/switchMap';
import {Person} from '../shared/person.model';
import {GlobalApp} from '../../helpers/global';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-profile-modal',
  templateUrl: './profile-modal.component.html',
  styleUrls: ['./profile-modal.component.scss']
})
export class ProfileModalComponent implements OnInit {
  @Input()
  person: Person;

  app: GlobalApp;

  wines: Wine[] = [];

  private url: String = 'http://localhost:8080/api/person/';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private personService: PersonService,
              public activeModal: NgbActiveModal) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.personService.getByUsername('http://localhost:8080/api/person/getbyusername/', this.person.username).subscribe(person => {
      // if (this.app.localStorageItem("username") === person.username) {
      //   this.app.setLocalStorage(person);
      // }
      this.wines = person.wines.apiGatewayWineDtoList;
    });
  }

  close(): void {
    this.activeModal.close();
  }

  seeDetails(id: string): void {
    this.router.navigateByUrl('/wine/view/' + id);
  }

  editWine(id: string): void {
    this.router.navigate(['/wine/edit/' + id]);
  }
}
