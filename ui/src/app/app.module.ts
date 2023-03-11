import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { EditorComponent } from './shared/components/code-editor/editor.component';
import { CodeEditorToolbarComponent } from './shared/components/code-editor-toolbar/code-editor-toolbar.component';
import {SharedModule} from "./shared/shared.module";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    SharedModule,
    HttpClientModule
  ],
  providers: [],
  exports: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
