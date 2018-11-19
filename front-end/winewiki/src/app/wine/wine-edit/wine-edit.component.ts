import {Component, Input, OnInit} from '@angular/core';
import {GlobalApp} from '../../helpers/global';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {WineService} from '../shared/wine.service';
import {WinetypeService} from '../shared/winetype.service';
import {Wine} from '../shared/wine.model';


export interface InterfaceAlert {
  id: number;
  type: string;
  message: string;
}

@Component({
  selector: 'app-wine-edit',
  templateUrl: './wine-edit.component.html',
  styleUrls: ['./wine-edit.component.scss']
})

export class WineEditComponent implements OnInit {


  wine: Wine = new Wine(0, '', '', '', '', new Date(), '', new Date(), '', 0, '', '');

  ids = 0;

  cities = ['Aiud', 'Alba Iulia', 'Alexandria', 'Anina', 'Arad', 'Azuga', 'Bacău', 'Baia Mare', 'Baia Sprie', 'Băicoi',
    'Băile Herculane', 'Băile Olăneşti', 'Bârlad', 'Beiuş', 'Bicaz', 'Bistriţa', 'Blaj', 'Borşa', 'Botoşani', 'Braşov',
    'Brăila', 'Bucureşti', 'Buftea', 'Buşteni', 'Buzău', 'Caracal', 'Caransebeş', 'Cavnic', 'Câmpeni', 'Câmpia Turzii',
    'Câmpulung Moldovenesc', 'Cisnădie', 'Cluj-Napoca', 'Comăneşti', 'Constanţa', 'Covasna', 'Craiova',
    'Curtea de Argeş', 'Dej', 'Deva', 'Dorohoi', 'Drăgăşani', 'Drobeta-Turnu Severin', 'Făgăraş', 'Fălticeni', 'Gherla',
    'Gura Humorului', 'Horezu', 'Huedin', 'Hunedoara', 'Iaşi', 'Ineu', 'Jibou', 'Luduş', 'Lugoj', 'Mangalia', 'Măcin',
    'Mărăşeşti', 'Mediaş', 'Miercurea Ciuc', 'Mioveni', 'Motru', 'Nădlac', 'Năsăud', 'Năvodari', 'Negru Vodă',
    'Ocna Mureş', 'Ocna Sibiului', 'Odorheiu Secuiesc', 'Oradea', 'Orăştie', 'Otopeni', 'Paşcani', 'Petroşani',
    'Piatra Neamţ', 'Piteşti', 'Ploieşti', 'Predeal', 'Rădăuţi', 'Râmnicu Sărat', 'Râmnicu Vâlcea', 'Râşnov',
    'Reghin', 'Reşiţa', 'Roman', 'Rupea', 'Satu Mare', 'Săcele', 'Sângeorz-Băi', 'Sebeş', 'Sfântu Gheorghe',
    'Sibiu', 'Sighetu Marmaţiei', 'Sighişoara', 'Sinaia', 'Slatina', 'Slănic', 'Sovata', 'Suceava',
    'Sulina', 'Şimleu Silvaniei', 'Ştei'];

  wineTypes: string[] = [];

  @Input() id: number;

  @Input() alertsUpdate: Array<InterfaceAlert> = [];

  app: GlobalApp;

  constructor(public formBuilder: FormBuilder,
              private router: Router,
              private wineService: WineService,
              private winetypeService: WinetypeService,
              private route: ActivatedRoute) {
    this.app = new GlobalApp();
  }

  // private _success = new Subject<string>();
  //   //
  //   // public changeSuccessMessage() {
  //   //   this._success.next(`${new Date()} - Message successfully changed.`);
  //   // }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.wineService.getWineById(params['id'], '/getbyid/'))
      .subscribe((data: Wine) => {
        this.wine = data;
      });
  }

  updateWine() {
    this.wineService.updateWine('update', this.wine)
      .subscribe(data => {
        this.alertsUpdate.push({
          id: this.ids++,
          type: 'success',
          message: 'Edit successful'
        });
        console.log(this.alertsUpdate);
      });
  }

  deleteWine() {
    if (confirm('Are you sure you want to delete your posted wine?')) {
      this.wineService.deleteWine('delete', this.wine).subscribe(data => {
        this.alertsUpdate.push({
          id: this.ids++,
          type: 'success',
          message: 'Deleted successfully'
        });
        console.log(this.alertsUpdate);
      });
    }
  }


  public closeAlert(alert: InterfaceAlert) {
    const index: number = this.alertsUpdate.indexOf(alert);
    this.alertsUpdate.splice(index, 1);
    console.log(this.alertsUpdate);
    this.router.navigateByUrl('profile/' + localStorage.getItem('username'));
  }

  checkNumber(): boolean {
    if (this.wine.cost.toString().length >= 2) {
      return !isNaN(Number(this.wine.cost));
    } else {
      return false;
    }
  }
  cancel(): void {
    this.router.navigateByUrl('profile/' + localStorage.getItem('username'));
  }
}
