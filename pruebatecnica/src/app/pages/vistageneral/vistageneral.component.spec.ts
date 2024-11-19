import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VistageneralComponent } from './vistageneral.component';

describe('VistageneralComponent', () => {
  let component: VistageneralComponent;
  let fixture: ComponentFixture<VistageneralComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VistageneralComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VistageneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
