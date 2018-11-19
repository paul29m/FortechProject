import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from './authentication.service';
import {PersonService} from '../profile/shared/person.service';
import {NewPerson} from '../profile/shared/newPerson.model';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  person: NewPerson = new NewPerson('', '', '', '');
  loading = false;
  loginBtnVisible = true;
  signUpVisible = false;

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


  constructor(private router: Router,
              private authenticationService: AuthenticationService,
              private personService: PersonService,
              public snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.logOutUser();
  }

  changeCity(newCity: string) {
    this.selectedCity = newCity;
    this.person.location = newCity;
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Dismiss', {
      duration: 3000
    });
  }

  logInUser() {
    this.loading = true;
    this.authenticationService.login(this.person.username, this.person.password)
      .subscribe(
        data => {
          // this.router.navigate(["profile", this.person.username]);
          // this.router.navigate(['/product-list'], { queryParams: { page: pageNum } });
          this.router.navigate(['wine/home'], {
            queryParams: {
              page: 0,
              size: 2
            }
          });
        },
        error => {
          this.openSnackBar('Username or password incorrect');
          this.loading = false;
        });
  }

  signUpUser() {
    this.personService.createPerson('/person/create', this.person)
      .subscribe(data => {
        this.logInNewUser();
      });
  }

  logInNewUser() {
    this.loading = true;
    this.authenticationService.login(this.person.username, this.person.password)
      .subscribe(data => {
        // this.router.navigate(['/profile/edit', this.person.username]);
        this.router.navigate(['wine/home'], {
          queryParams: {
            page: 0,
            size: 2
          }
        });
      });
  }

  expandSignUp(event) {
    this.loginBtnVisible = false;
    this.signUpVisible = true;
  }

  hideSignUp(event) {
    this.loginBtnVisible = true;
    this.signUpVisible = false;
  }

  private logOutUser(): void {
    this.authenticationService.logout();
  }
}
