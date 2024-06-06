import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OcustomerOrdersComponent } from './ocustomer-orders.component';

describe('OcustomerOrdersComponent', () => {
  let component: OcustomerOrdersComponent;
  let fixture: ComponentFixture<OcustomerOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OcustomerOrdersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OcustomerOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
