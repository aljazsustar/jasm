import {Component, OnInit, ViewChild} from '@angular/core';
import {ApiService} from "../services/api.service";
import {CodeEditorComponent} from "@ngstack/code-editor";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{

  @ViewChild('codeEditor')
  public codeEditor: CodeEditorComponent | undefined;

  public constantPoolInfo: any;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.apiService.compileSource().subscribe(res => console.log(res));
  }

  onCompile() {
    this.apiService.compileSource(this.codeEditor?.codeModel?.value).subscribe(res => this.constantPoolInfo = res);
  }

}
