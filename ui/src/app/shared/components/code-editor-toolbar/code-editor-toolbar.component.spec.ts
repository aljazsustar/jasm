import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeEditorToolbarComponent } from './code-editor-toolbar.component';

describe('CodeEditorToolbarComponent', () => {
  let component: CodeEditorToolbarComponent;
  let fixture: ComponentFixture<CodeEditorToolbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CodeEditorToolbarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CodeEditorToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
