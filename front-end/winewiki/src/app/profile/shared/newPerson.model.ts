export class NewPerson {
  public username: string;
  public password: string;
  public location: string;
  public email: string;

  constructor(username: string, password: string, location: string, email: string) {
    this.username = username;
    this.password = password;
    this.location = location;
    this.email = email;
  }
}
