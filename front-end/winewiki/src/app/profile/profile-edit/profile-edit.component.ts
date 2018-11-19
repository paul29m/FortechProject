import {PersonService} from '../shared/person.service';

import {Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';

import 'rxjs/add/operator/switchMap';
import {Person} from '../shared/person.model';
import {GlobalApp} from '../../helpers/global';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.scss']
})
export class ProfileEditComponent implements OnInit {

  // TODO use model class
  person = {
    'id': localStorage.getItem('personId'),
    'username': localStorage.getItem('username'),
    'password': '',
    'location': localStorage.getItem('location'),
    'firstname': localStorage.getItem('firstname'),
    'lastname': localStorage.getItem('lastname'),
    'description': localStorage.getItem('description'),
    'phonenumber': localStorage.getItem('phonenumber'),
    'picture': localStorage.getItem('picture')
  };
  fileList: FileList;
  app: GlobalApp;
  private url: String = 'http://localhost:8080/api/person/';

  // TODO use form to put data
  constructor(public formBuilder: FormBuilder,
              private router: Router,
              private personService: PersonService) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.personService.getByUsername('getbyusername/', localStorage.getItem('username')).subscribe(thePerson => {
      this.person = thePerson;
      this.person.phonenumber = thePerson.phonenumber;
    });
  }

  cancel(): void {
    this.router.navigate(['profile', this.person.username]);
  }

  deleteCancel(): void {
    this.router.navigate(['wine', 'home']);
  }

  fileChange(e) {
    this.fileList = e.target.files;
  }

  editProfile() {
    if (this.fileList != null) {
      const file: File = this.fileList[0];
      const formData: FormData = new FormData();
      formData.append('file', file, file.name);
      formData.append('person', new Blob([JSON.stringify(this.person)], {
        type: 'application/json'
      }));
      this.personService.updateWithFile(this.url + '/update/image', formData)
        .subscribe(
          data => {
            const person = new Person(data.json().username,
              '',
              data.json().location,
              data.json().id,
              data.json().firstname,
              data.json().lastname,
              data.json().description,
              data.json().phonenumber,
              data.json().picture
            );
            if (localStorage.getItem('personId') === person.id.toString()) {
              this.app.setLocalStorage(person);
            }
            this.cancel();
          },
          error => console.log(error)
        );
    } else {
      const formData: FormData = new FormData();
      formData.append('person', new Blob([JSON.stringify(this.person)], {
        type: 'application/json'
      }));
      this.personService.updateWithFile(this.url + '/update/noimage', formData)
        .subscribe(
          data => {
            const person = new Person(data.json().username,
              '',
              data.json().location,
              data.json().id,
              data.json().firstname,
              data.json().lastname,
              data.json().description,
              data.json().phonenumber,
              null
            );
            if (localStorage.getItem('personId') === person.id.toString()) {
              this.app.setLocalStorage(person);
            }
            this.cancel();
          },
          error => console.log(error)
        );
    }
  }

  checkPhoneNumber(): boolean {
    if (this.person.phonenumber.length === 10) {
      return !isNaN(Number(this.person.phonenumber.toString()));
    } else {
      return false;
    }
  }
  deleteProfile() {
    if (confirm('Are you sure you want to delete your profile?')) {
      this.personService.deletePerson('/person/delete', this.person).subscribe(data => {
        this.personService.getPersonsDelete();
      });
      this.router.navigate(['/login']);
    } else {
      this.deleteCancel();
    }
  }
}
