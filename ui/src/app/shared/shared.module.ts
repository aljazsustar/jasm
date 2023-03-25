import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CodeEditorToolbarComponent} from "./components/code-editor-toolbar/code-editor-toolbar.component";
import {CodeEditorModule} from "@ngstack/code-editor";
import {EditorComponent} from "./components/code-editor/editor.component";
import { TopbarComponent } from './components/topbar/topbar.component';
import {RouterLink} from "@angular/router";



@NgModule({
  declarations: [
    EditorComponent,
    CodeEditorToolbarComponent,
    TopbarComponent
  ],
    exports: [
        EditorComponent,
        CodeEditorToolbarComponent,
        TopbarComponent
    ],
  imports: [
    CommonModule,
    CodeEditorModule.forRoot(),
    RouterLink
  ]
})
export class SharedModule { }
