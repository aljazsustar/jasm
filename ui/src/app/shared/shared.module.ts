import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CodeEditorToolbarComponent} from "./components/code-editor-toolbar/code-editor-toolbar.component";
import {CodeEditorModule} from "@ngstack/code-editor";
import {EditorComponent} from "./components/code-editor/editor.component";



@NgModule({
  declarations: [
    EditorComponent,
    CodeEditorToolbarComponent
  ],
  exports: [
    EditorComponent,
    CodeEditorToolbarComponent
  ],
  imports: [
    CommonModule,
    CodeEditorModule.forRoot()
  ]
})
export class SharedModule { }
