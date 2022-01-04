import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TelaerrorComponent } from './telaerror.component';

describe('TelaerrorComponent', () => {
  let component: TelaerrorComponent;
  let fixture: ComponentFixture<TelaerrorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TelaerrorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TelaerrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
