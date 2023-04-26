import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CodeEditorToolbarComponent} from "./components/code-editor-toolbar/code-editor-toolbar.component";
import {CodeEditorModule} from "@ngstack/code-editor";
import {EditorComponent} from "./components/code-editor/editor.component";
import {TopbarComponent} from './components/topbar/topbar.component';
import {RouterLink} from "@angular/router";
import {ConstantPoolComponent} from './components/constant-pool/constant-pool.component';
import {ToastComponent} from './components/toast/toast.component';
import {ToasterComponent} from './components/toaster/toaster.component';
import {NgbToast} from "@ng-bootstrap/ng-bootstrap";


@NgModule({
  declarations: [
    EditorComponent,
    CodeEditorToolbarComponent,
    TopbarComponent,
    ConstantPoolComponent,
    ToastComponent,
    ToasterComponent
  ],
  exports: [
    EditorComponent,
    CodeEditorToolbarComponent,
    TopbarComponent,
    ConstantPoolComponent,
    ToastComponent,
    ToasterComponent
  ],
  imports: [
    CommonModule,
    CodeEditorModule.forRoot(),
    RouterLink,
    NgbToast
  ]
})
export class SharedModule { }
