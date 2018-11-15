export class Page {
  public content: any[];
  public first: boolean;
  public last: boolean;
  public number: number;
  public numberOfElements: number;
  public size: number;
  public sort: string;
  public totalElements: number;
  public totalPages: number;

  constructor(content: any[],
              first: boolean,
              last: boolean,
              number: number,
              numberOfElements: number,
              size: number,
              sort: string,
              totalElements: number,
              totalPages: number) {
    this.content = content;
    this.first = first;
    this.last = last;
    this.number = number;
    this.numberOfElements = numberOfElements;
    this.size = size;
    this.sort = sort;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
  }
}
