import {Person} from '../profile/shared/person.model';

export class GlobalApp {

  constructor() {
  }

  public localStorageItem(id: string): string {
    if (localStorage.getItem(id)) {
      return localStorage.getItem(id);
    } else {
      return '';
    }
  }

  public setLocalStorage(person: Person) {
    if (person.id) {
      localStorage.setItem('personId', person.id.toString());
    } else {
      localStorage.setItem('personId', '');
    }
    localStorage.setItem('username', person.username);
    if (person.location) {
      localStorage.setItem('location', person.location);
    } else {
      localStorage.setItem('location', '');
    }
    if (person.firstname) {
      localStorage.setItem('firstname', person.firstname);
    } else {
      localStorage.setItem('firstname', '');
    }
    if (person.lastname) {
      localStorage.setItem('lastname', person.lastname);
    } else {
      localStorage.setItem('lastname', '');
    }
    if (person.description) {
      localStorage.setItem('description', person.description);
    } else {
      localStorage.setItem('description', '');
    }
    if (person.picture) {
      localStorage.setItem('picture', person.picture);
    } else {
      localStorage.setItem('picture', '');
    }
  }
}

