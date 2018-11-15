import {Component, Input, OnInit} from '@angular/core';
import {GlobalApp} from '../../helpers/global';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {WineService} from '../shared/wine.service';
import {Wine} from '../shared/wine.model';
import {Page} from '../shared/page.model';
import {WinetypeService} from '../shared/winetype.service';
import {WineType} from '../shared/winetype.model';
import {PersonService} from '../../profile/shared/person.service';

@Component({
  selector: 'app-wine-home',
  templateUrl: './wine-home.component.html',
  styleUrls: ['./wine-home.component.scss']
})
export class WineHomeComponent implements OnInit {

  app: GlobalApp;

  wines: Wine[][] = [];

  wineTypes: WineType[] = [];

  type: string;

  nrOfPages = 0;

  isFirstPage = true;

  isLastPage = true;

  pageNumber: number;

  pages: number[] = [];

  totalNumberOfWines = 0;

  spinners = true;

  location: string;

  cities: string[] = ['All', 'Aiud', 'Alba Iulia', 'Alexandria', 'Anina', 'Arad', 'Azuga', 'Bacău', 'Baia Mare', 'Baia Sprie', 'Băicoi',
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


  constructor(private router: Router,
              private route: ActivatedRoute,
              private wineService: WineService,
              private winetypeService: WinetypeService,
              private personService: PersonService) {
    this.app = new GlobalApp();
  }

  @Input()
  set size(newSize: number) {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: 0,
        size: newSize
      },
      queryParamsHandling: 'merge'
    });
  }

  @Input()
  set available_until(newAvailableDate: string) {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: 0,
        available_until: this.getDateString(newAvailableDate)
      },
      queryParamsHandling: 'merge'
    });
  }

  ngOnInit() {
    if (!this.app.localStorageItem('personId')) {
      this.personService.getByUsername('http://localhost:8080/api/person/getbyusername/', this.app.localStorageItem('username')).subscribe(person => {
        if (this.app.localStorageItem('username') === person.username) {
          this.app.setLocalStorage(person);
        }
      });
    }
    this.location = this.app.localStorageItem('location');
    this.route.queryParams
      .subscribe((params: Params) => {
        this.totalNumberOfWines = 0;
        this.type = 'All';
        if (params.hasOwnProperty('type')) {
          this.type = params['type'];
        }
        let location: string = null;
        if (params.hasOwnProperty('location')) {
          location = params['location'];
        } else if (this.location === this.app.localStorageItem('location')) {
          location = this.app.localStorageItem('location');
        }
        let start_time: string = null;
        if (params.hasOwnProperty('start_time')) {
          start_time = params['start_time'];
        }
        let end_time: string = null;
        if (params.hasOwnProperty('end_time')) {
          end_time = params['end_time'];
        }
        let available_until: string = null;
        if (params.hasOwnProperty('available_until')) {
          available_until = params['available_until'];
        }
        this.wineService.getWines('/getall',
          params['page'], params['size'], this.type, location, start_time, end_time, available_until).subscribe(
          (data: Page) => {
            this.pages = [];
            this.wines = data.content;
            // this.size = data.size;
            this.nrOfPages = data.totalPages;
            this.isFirstPage = data.first;
            this.isLastPage = data.last;
            this.pageNumber = data.number;
            for (let i = 1; i <= this.nrOfPages; i++) {
              this.pages.push(i);
            }
          });
        this.winetypeService.getAll().subscribe(
          (data: WineType[]) => {
            this.wineTypes = data;
            this.wineTypes.forEach(wineType => {
              this.totalNumberOfWines += wineType.wines.length;
            });
          }
        );
      });
  }

  chooseWineType(newType): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: 0,
        type: newType
      },
      queryParamsHandling: 'merge'
    });
  }

  changeCity(newLocation: string) {
    if (newLocation === 'All') {
      newLocation = '';
    }
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: 0,
        location: newLocation
      },
      queryParamsHandling: 'merge'
    });
  }

  seeDetails(id: string): void {
    this.router.navigateByUrl('wine/view/' + id);
  }

  goToPage(page: number): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: page - 1,
      },
      queryParamsHandling: 'merge'
    });
  }

  goToPreviousPage(): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: this.pageNumber - 1,
      },
      queryParamsHandling: 'merge'
    });
  }

  goToNextPage(): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: this.pageNumber + 1,
      },
      queryParamsHandling: 'merge'
    });
  }

  private getDateString(date: string): string {
    if (date) {
      return new Date(date).getFullYear()
        + '-' + (new Date(date).getMonth() + 1) + '-' +
        new Date(date).getDate();
    }
    return '';
  }

  private getTimeString(time: { hour: number, minute: number }): string {
    if (time) {
      return time.hour + ':' + time.minute + ':00';
    }
    return '';
  }
}

