import { TestBed } from '@angular/core/testing';

import { LoginRegistrationPageService } from './login-registration-page.service';

describe('LoginRegistrationPageService', () => {
  let service: LoginRegistrationPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginRegistrationPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
