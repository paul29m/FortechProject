import {Component, OnInit} from '@angular/core';
import {GlobalApp} from '../../helpers/global';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {
  test: Date = new Date();

  app: GlobalApp;

  constructor() {
    this.app = new GlobalApp();
  }

  ngOnInit() {
  }
}
