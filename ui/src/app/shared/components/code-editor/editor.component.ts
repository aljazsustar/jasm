import { Component } from '@angular/core';
import {CodeModel} from "@ngstack/code-editor";

@Component({
  selector: 'app-code-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.scss']
})
export class EditorComponent {
  theme = 'vs-dark';

  codeModel: CodeModel = {
    language: 'java',
    uri: 'Main.java',
    value: `public class Main {
  public static void main(String[] args) {
  }
}
    `
  };

  options = {
    contextmenu: true,
  };

  onCodeChanged(value: any) {
    console.log('CODE', this.codeModel.value);
  }}
