export class Wine {
  public id: number;
  public title: string;
  public description: string;
  public status: string;
  public location: string;
  public startDate: Date;
  public startTime: string;
  public endDate: Date;
  public endTime: string;
  public cost: number;
  public owner: string;
  public wineType: string;

  constructor(id: number,
              title: string,
              description: string,
              status: string,
              location: string,
              startDate: Date,
              startTime: string,
              endDate: Date,
              endTime: string,
              cost: number,
              owner: string,
              wineType: string) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.owner = owner;
    this.location = location;
    this.startDate = startDate;
    this.startTime = startTime;
    this.endDate = endDate;
    this.endTime = endTime;
    this.cost = cost;
    this.wineType = wineType;
  }
}
