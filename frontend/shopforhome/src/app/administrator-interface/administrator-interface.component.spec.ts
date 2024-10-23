import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministratorInterfaceComponent } from './administrator-interface.component';

describe('AdministratorInterfaceComponent', () => {
  let component: AdministratorInterfaceComponent;
  let fixture: ComponentFixture<AdministratorInterfaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministratorInterfaceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdministratorInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
