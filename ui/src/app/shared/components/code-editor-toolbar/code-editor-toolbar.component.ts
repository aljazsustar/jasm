import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-code-editor-toolbar',
  templateUrl: './code-editor-toolbar.component.html',
  styleUrls: ['./code-editor-toolbar.component.scss']
})
export class CodeEditorToolbarComponent {

  @Output()
  public onCompile = new EventEmitter();

  @Output()
  public onSaveToFile = new EventEmitter();

  @Output()
  public onSaveToClassFile = new EventEmitter();

  @Input()
  public showSaveClassFile: boolean = false;

  onCompileClicked() {
    this.onCompile.emit();
  }

  onSaveToFileClicked() {
    this.onSaveToFile.emit();
  }

  onSaveToClassFileClicked() {
    this.onSaveToClassFile.emit();
  }
}
