import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InformacionbasicaComponent } from './informacionbasica.component';

describe('InformacionbasicaComponent', () => {
  let component: InformacionbasicaComponent;
  let fixture: ComponentFixture<InformacionbasicaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InformacionbasicaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InformacionbasicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
