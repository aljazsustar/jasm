import {Component, OnInit, ViewChild} from '@angular/core';
import {ApiService} from "../services/api.service";
import {ClassFile} from "../services/models/classFile";
import {EditorComponent} from "../shared/components/code-editor/editor.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @ViewChild('codeEditor')
  public codeEditor: EditorComponent | undefined;

  public constantPoolInfo: ClassFile | undefined;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  onCompile() {
    this.apiService.compileSource(this.codeEditor?.codeModel?.value).subscribe(res => this.constantPoolInfo = res.classFile);
  }

}
