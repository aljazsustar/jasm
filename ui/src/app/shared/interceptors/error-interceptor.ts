import {Injectable} from "@angular/core";
import {HttpEvent, HttpEventType, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {ToastService} from "../../services/toast.service";
import {map, Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private toastService: ToastService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(map(event => {
        if (event.type === HttpEventType.Response && event.body.error) {
          this.toastService.showErrorToast(event.body.error.summary, event.body.error.details);
        }
        return event;
      }
    ));
  }
}
