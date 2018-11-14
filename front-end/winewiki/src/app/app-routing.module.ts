import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from './guards/auth.guard';
import {ProfileViewComponent} from './profile/profile-view/profile-view.component';
import {ProfileEditComponent} from './profile/profile-edit/profile-edit.component';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {WineViewComponent} from './wine/wine-view/wine-view.component';
import {WineAddComponent} from './wine/wine-add/wine-add.component';
import {WineHomeComponent} from './wine/wine-home/wine-home.component';
import {WineEditComponent} from './wine/wine-edit/wine-edit.component';

const routes: Routes = [{
  path: '',
  redirectTo: 'login',
  pathMatch: 'full',
  canActivate: [AuthGuard]
},
  {
    path: 'profile/:username',
    canActivate: [AuthGuard],
    component: ProfileViewComponent
  },

  {
    path: 'login',
    component: LoginComponent
  },

  {
    path: 'profile/edit/:username',
    component: ProfileEditComponent
  },

  {
    path: 'wine/view/:id',
    component: WineViewComponent

  },
  {
    path: 'wine/home',
    component: WineHomeComponent
  },
  {
    path: 'wine/create',
    // canActivate: [AuthGuard],
    component: WineAddComponent
  },
  {
    path: 'wine/edit/:id',
    component: WineEditComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [],
})
export class AppRoutingModule {
}
