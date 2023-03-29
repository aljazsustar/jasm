import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Result} from "./models/classFile";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private headers = new HttpHeaders({'Content-Type': 'text/plain'});
  private apiUrl: string = 'http://localhost:8080/v1/compile';

  constructor(private http: HttpClient) {
  }

  compileSource(fileContent?: string) {
    return this.http.post<Result>(`${this.apiUrl}/getInfo`, fileContent);
  }

  getClassFile() {
    return this.http.get<any>(`${this.apiUrl}/classFile`);
  }
}
