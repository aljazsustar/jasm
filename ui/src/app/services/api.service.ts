import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ClassFile, Result} from "./models/classFile";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private headers = new HttpHeaders({'Content-Type': 'text/plain'});
  private apiUrl: string = 'http://0.0.0.0:8080/v1/compile';

  private fileContent = "import com.example.insert.annotations.Block;\n" +
    "import com.example.insert.annotations.Jasm;\n" +
    "\n" +
    "public class Main {\n" +
    "\n" +
    "  @Jasm({\n" +
    "    @Block(start=11, end=15)\n" +
    "  })\n" +
    "  public static void main(String[] args) {\n" +
    "    /*\n" +
    "     getstatic 7\n" +
    "     bipush 8\n" +
    "     iconst_5\n" +
    "     invokestatic 13\n" +
    "     invokevirtual 19\n" +
    "    */\n" +
    "    System.out.println(sum(8, 5));\n" +
    "  }\n" +
    "\n" +
    "  public static int sum(int a, int b) {\n" +
    "       return a+b;\n" +
    "  }\n" +
    "}"

  constructor(private http: HttpClient) { }

  compileSource(fileContent?: string) {
    return this.http.post<Result>(`${this.apiUrl}/getInfo`, this.fileContent, {headers: this.headers});
  }
}
