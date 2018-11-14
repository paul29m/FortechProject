import {Location} from '@angular/common';
import {Component, ElementRef, Inject, Input, OnDestroy, OnInit, Renderer} from '@angular/core';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import 'rxjs/add/operator/filter';
import {DOCUMENT} from '@angular/platform-browser';
import {GlobalApp} from '../../helpers/global';

export interface IAlert {
  id: number;
  type: string;
  message: string;
  icon?: string;
}

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnDestroy {

  @Input() isTransparent: boolean;

  @Input()
  public alerts: Array<IAlert> = [];

  app: GlobalApp;

  ids = 0;

  private toggleButton: any;
  private alive: boolean;

  private sidebarVisible: boolean;


  private _router: Subscription;

  constructor(private element: ElementRef,
              private renderer: Renderer,
              private router: Router, @Inject(DOCUMENT,)
              private document: any,
              public location: Location) {
    this.sidebarVisible = false;
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.alive = true;
  }

  sidebarOpen() {
    const toggleButton = this.toggleButton;
    const html = document.getElementsByTagName('html')[0];
    setTimeout(function () {
      toggleButton.classList.add('toggled');
    }, 500);
    html.classList.add('nav-open');

    this.sidebarVisible = true;
  }

  sidebarClose() {
    const html = document.getElementsByTagName('html')[0];
    this.toggleButton.classList.remove('toggled');
    this.sidebarVisible = false;
    html.classList.remove('nav-open');
  }

  sidebarToggle() {
    if (this.sidebarVisible === false) {
      this.sidebarOpen();
    } else {
      this.sidebarClose();
    }
  }

  isHome() {
    const titlee = this.location.prepareExternalUrl(this.location.path());

    if (titlee === '/home') {
      return true;
    } else {
      return false;
    }
  }

  ngOnDestroy() {
    this.alive = false;
  }
}
