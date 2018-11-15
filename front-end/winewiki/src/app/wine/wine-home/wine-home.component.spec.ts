import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {WineHomeComponent} from './wine-home.component';

describe('WineHomeComponent', () => {
  let component: WineHomeComponent;
  let fixture: ComponentFixture<WineHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [WineHomeComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WineHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
