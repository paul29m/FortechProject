export class Review {
  public message: string;
  public positive: boolean;
  public reviewer: string;
  public picture: string;

  constructor(message: string, positive: boolean, reviewer: string, picture?: string) {
    this.message = message;
    this.positive = positive;
    this.reviewer = reviewer;
    this.picture = picture;
  }
}
