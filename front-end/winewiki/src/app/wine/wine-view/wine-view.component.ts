import {Component, Input, OnInit} from '@angular/core';
import {GlobalApp} from '../../helpers/global';
import {Wine} from '../shared/wine.model';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {WineService} from '../shared/wine.service';
import {Person} from '../../profile/shared/person.model';
import {ProfileModalComponent} from '../../profile/profile-modal/profile-modal.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {PersonService} from '../../profile/shared/person.service';

@Component({
  selector: 'app-wine-view',
  templateUrl: './wine-view.component.html',
  styleUrls: ['./wine-view.component.scss']
})
export class WineViewComponent implements OnInit {

  wine: Wine = new Wine(0, '', '', '', '', new Date(), '', new Date(), '', 0, '', '');
  person: Person = new Person();
  winePerson: Person = new Person();
  descrTruncated: string;
  viewDescription: string;
  photo: string;

  app: GlobalApp;

  @Input() id: number;

  private url: String = 'http://localhost:8080/api/wine/';
  private urlPerson: String = 'http://localhost:8080/api/person/';


  constructor(private route: ActivatedRoute,
              private wineService: WineService,
              private personService: PersonService,
              private router: Router,
              private modalService: NgbModal) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.wineService.getWineById(params.id, '/getbyid/'))
      .subscribe((res: Wine) => {
        this.wine = res;
        if (this.wine.description != null) {
          this.descrTruncated = this.wine.description.slice(0, 170);
        }
        this.viewDescription = '...View More';
        console.log(res);
        this.winePerson.username = this.wine.owner;
        this.setWinePerson();
      });

    this.getUser();
  }

  getUser() {
    this.route.params
      .switchMap((params: Params) => this.personService.getByUsername(this.urlPerson + 'getbyusername/', localStorage.getItem('username')))
      .subscribe(person => {
        if (this.app.localStorageItem('username') === person.username) {
          this.app.setLocalStorage(person);
        }
        this.person = person;
        this.photo = 'data:image/jpg;base64,' + person.picture;
      });
  }

  showMore(): void {
    console.log(this.wine.description);
    if (this.viewDescription === '...View More') {
      this.descrTruncated = this.wine.description;
      this.viewDescription = '...View Less';
    }
    else if (this.viewDescription === '...View Less') {
      this.descrTruncated = this.wine.description.slice(0, 170);
      this.viewDescription = '...View More';
    }
  }

  launchModal(content: Person, wineId: number): void {
    const modalRef = this.modalService.open(ProfileModalComponent);
    modalRef.componentInstance.person = content;
    modalRef.componentInstance.wineId = wineId;
  }

  edit(): void {
    this.router.navigateByUrl('/wine/edit/' + this.wine.id);
  }

  private setWinePerson() {
    this.personService.getByUsername(this.urlPerson + 'getbyusername/', this.wine.owner).subscribe(person => {
      this.winePerson = person;
    });
  }
}
