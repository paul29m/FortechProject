import {Component, OnInit} from '@angular/core';
import {GlobalApp} from '../../helpers/global';
import {Router} from '@angular/router';
import {WineService} from '../shared/wine.service';
import {NewWine} from '../shared/newwine.model';
import {WinetypeService} from '../shared/winetype.service';
import {WineType} from '../shared/winetype.model';

@Component({
  selector: 'app-wine-add',
  templateUrl: './wine-add.component.html',
  styleUrls: ['./wine-add.component.scss']
})
export class WineAddComponent implements OnInit {

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
  selectedCity = 'Location';
  selectedWineType = 'Type';
  fileList: FileList;
  wineTypes: string[] = [];
  wine: NewWine = new NewWine('', '', '', new Date(), new Date(), 0, localStorage.getItem('username'), 'Type');
  app: GlobalApp;
  private url: String = 'http://localhost:8080/api/wine/';

  constructor(private router: Router,
              private wineService: WineService,
              private winetypeService: WinetypeService) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.winetypeService.getAll().subscribe((wineTypes1: WineType[]) => {
      this.wineTypes = wineTypes1.map(wineType => wineType.name);
    });
  }

  cancel(): void {
    this.router.navigateByUrl('profile/' + localStorage.getItem('username'));
  }

  createWine() {
    if (this.wine.wineType === 'Type') {
      // TODO
    } else {
      this.wineService.createWine(this.url + 'create', this.wine)
        .subscribe(data => {
          this.router.navigate(['profile', localStorage.getItem('username')]);
        });
    }
  }


  changeCity(newCity: string): void {
    this.selectedCity = newCity;
    this.wine.location = newCity;
  }

  changeWineType(newWineType: string): void {
    this.selectedWineType = newWineType;
    this.wine.wineType = newWineType;
  }

}
