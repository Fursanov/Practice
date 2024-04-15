import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpEvent,
  HttpErrorResponse
} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor() {  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
          errorMessage = `Error: ${error.error.message}`;
        } else {
          errorMessage = `Error Code: ${error.status}\n Message: ${error.message}`;
        }
        alert(errorMessage);
        return throwError(errorMessage);
      })
    );
  }
}
