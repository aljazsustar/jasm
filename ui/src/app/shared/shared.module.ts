import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CodeEditorToolbarComponent} from "./components/code-editor-toolbar/code-editor-toolbar.component";
import {CodeEditorModule} from "@ngstack/code-editor";
import {EditorComponent} from "./components/code-editor/editor.component";
import {TopbarComponent} from './components/topbar/topbar.component';
import {RouterLink} from "@angular/router";
import {ConstantPoolComponent} from './components/constant-pool/constant-pool.component';


@NgModule({
  declarations: [
    EditorComponent,
    CodeEditorToolbarComponent,
    TopbarComponent,
    ConstantPoolComponent
  ],
    exports: [
      EditorComponent,
      CodeEditorToolbarComponent,
      TopbarComponent,
      ConstantPoolComponent
    ],
  imports: [
    CommonModule,
    CodeEditorModule.forRoot(),
    RouterLink
  ]
})
export class SharedModule { }
