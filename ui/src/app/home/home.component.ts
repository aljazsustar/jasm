import {Component, OnInit, ViewChild} from '@angular/core';
import {ApiService} from "../services/api.service";
import {Result} from "../services/models/classFile";
import {EditorComponent} from "../shared/components/code-editor/editor.component";
import {Buffer} from 'buffer';

window.Buffer = Buffer;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @ViewChild('codeEditor')
  public codeEditor: EditorComponent | undefined;

  public result: Result | undefined;
  public showSaveClassFile = false;

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  onCompile() {
    this.apiService.compileSource(this.codeEditor?.codeModel?.value).subscribe(res => {
      this.result = res;
      this.showSaveClassFile = true;
    });
  }

  onSaveToClassFile() {
    let downloadLink = document.createElement('a');
    var b64encoded = Buffer.from(new Uint8Array(this.result!.executionResult.compiledClassFile));
    downloadLink.href = window.URL.createObjectURL(new Blob([b64encoded], {type: 'application/octet-stream'}));
    downloadLink.setAttribute('download', 'Main.class');
    document.body.appendChild(downloadLink);
    downloadLink.click();
    downloadLink.parentNode?.removeChild(downloadLink);
  }

  onSaveSourceToFile() {
    let file = new Blob([this.codeEditor!.codeModel.value], {type: '.java'});
    let a = document.createElement("a"),
      url = URL.createObjectURL(file);
    a.href = url;
    a.download = 'Main.java';
    document.body.appendChild(a);
    a.click();
    setTimeout(function () {
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    }, 0);
  }

}
