export class NewWine {
  public title: string;
  public description: string;
  public location: string;
  public startDate: Date;
  public endDate: Date;
  public cost: number;
  public owner: string;
  public wineType: string;

  constructor(
    title: string,
    description: string,
    location: string,
    startDate: Date,
    endDate: Date,
    cost: number,
    owner: string,
    wineType: string) {
    this.title = title;
    this.description = description;
    this.owner = owner;
    this.location = location;
    this.startDate = startDate;
    this.endDate = endDate;
    this.cost = cost;
    this.wineType = wineType;
  }
}
