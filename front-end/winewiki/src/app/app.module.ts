import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {HttpModule} from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';

import {ProfileViewComponent} from './profile/profile-view/profile-view.component';
import {ProfileEditComponent} from './profile/profile-edit/profile-edit.component';
import {AppRoutingModule} from './app-routing.module';
import {AuthenticationService} from './login/authentication.service';
import {AuthGuard} from './guards/auth.guard';
import {GlobalApp} from './helpers/global';
import {PersonService} from './profile/shared/person.service';
import {NavbarComponent} from './shared/navbar/navbar.component';
import {FooterComponent} from './shared/footer/footer.component';
import {AngularMaterialModule} from './angular-material.module';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from '@angular/router';
import {WineViewComponent} from './wine/wine-view/wine-view.component';
import {WineService} from './wine/shared/wine.service';
import {WineAddComponent} from './wine/wine-add/wine-add.component';
import {WineHomeComponent} from './wine/wine-home/wine-home.component';
import {WinetypeService} from './wine/shared/winetype.service';
import {ProfileModalComponent} from './profile/profile-modal/profile-modal.component';
import {WineEditComponent} from './wine/wine-edit/wine-edit.component';
import {ReviewService} from './profile/shared/review.service';

@NgModule({
  entryComponents: [
    ProfileModalComponent
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileViewComponent,
    ProfileEditComponent,
    ProfileModalComponent,
    NavbarComponent,
    FooterComponent,
    WineViewComponent,
    WineAddComponent,
    WineHomeComponent,
    WineEditComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    AppRoutingModule
  ],
  providers: [
    AuthenticationService,
    AuthGuard,
    GlobalApp,
    PersonService,
    WineService,
    WinetypeService,
    ReviewService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
