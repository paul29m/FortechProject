import {Component, OnInit, ViewChild} from '@angular/core';
import 'rxjs/add/operator/filter';
import {Location} from '@angular/common';
import {NavbarComponent} from './shared/navbar/navbar.component';
import {GlobalApp} from './helpers/global';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  @ViewChild(NavbarComponent) navbar: NavbarComponent;

  title = 'WineWiki';

  isTransparent = true;

  app: GlobalApp;

  constructor(public location: Location) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
  }

  removeNavBar(): boolean {
    let titlee = this.location.prepareExternalUrl(this.location.path());
    titlee = titlee.slice(1);
    return titlee !== 'login';
  }

  removeFooter(): boolean {
    let titlee = this.location.prepareExternalUrl(this.location.path());
    titlee = titlee.slice(1);
    return titlee !== 'login';
  }
}
