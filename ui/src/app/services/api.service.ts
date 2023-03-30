import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Result} from "./models/classFile";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl: string = `http://localhost:8080/v1/compile`;

  constructor(private http: HttpClient) {
  }

  compileSource(fileContent?: string) {
    return this.http.post<Result>(`${this.apiUrl}/getInfo`, fileContent);
  }
}
