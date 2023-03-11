import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private headers = new HttpHeaders({'Content-Type': 'text/plain'});
  private apiUrl: string = 'http://0.0.0.0:8080/v1/compile';

  constructor(private http: HttpClient) { }

  compileSource(fileContent?: string) {
    return this.http.post(`${this.apiUrl}/getInfo`, fileContent, {headers: this.headers});
  }
}
