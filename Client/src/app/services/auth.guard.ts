import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

export const authGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const cookieService = inject(CookieService);

  debugger
  const cookieData = cookieService.get("token");
  if (!cookieData) {
    router.navigateByUrl('/')
    return false;
  }
  return true;
};
