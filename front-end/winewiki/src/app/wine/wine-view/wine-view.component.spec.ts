import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {WineViewComponent} from './wine-view.component';

describe('WineViewComponent', () => {
  let component: WineViewComponent;
  let fixture: ComponentFixture<WineViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [WineViewComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WineViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
