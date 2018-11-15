export class WineType {
  public id: number;
  public name: string;
  public wines: string[];

  constructor(id: number, name: string, wines?: string[]) {
    this.id = id;
    this.name = name;
    this.wines = wines;
  }
}
