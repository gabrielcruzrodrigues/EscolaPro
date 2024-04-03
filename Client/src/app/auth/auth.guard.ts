import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

export const authGuard: CanActivateFn = (route, state) => {
  
  const router = inject(Router);
  const cookieService = inject(CookieService);

  debugger
  const cookieExists: boolean = cookieService.check('token');

  if (!cookieExists) {
    router.navigateByUrl('/')
    return false;
  }
  return true;
};
